package com.atolow.miixs.data.websocket

import android.util.Log
import com.atolow.miixs.BuildConfig
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.data.model.chat.WebSocketMessageDto
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompMessage

class WebSocketManager private constructor() {
    private var stompClient: StompClient? = null
    private val disposables = CompositeDisposable()
    private val gson = Gson()
    private var isManualDisconnect = false // 수동으로 연결을 끊었는지 여부
    private var reconnectAttempts = 0 // 재연결 시도 횟수
    private val maxReconnectAttempts = 5 // 최대 재연결 시도 횟수
    private val reconnectDelayMs = 3000L // 재연결 대기 시간 (밀리초)
    private val connectionListeners = mutableListOf<() -> Unit>() // 연결 성공 시 호출할 리스너들
    @Volatile
    private var isReconnecting = false // 재연결이 진행 중인지 여부
    
    companion object {
        private const val TAG = "WebSocketManager"
        private val WS_URL = BuildConfig.WS_URL
        
        @Volatile
        private var INSTANCE: WebSocketManager? = null
        
        fun getInstance(): WebSocketManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: WebSocketManager().also { INSTANCE = it }
            }
        }
    }
    
    fun connect() {
        if (stompClient?.isConnected == true) {
            Log.d(TAG, "Already connected")
            return
        }
        
        val token = TokenManager.getAccessToken()
        if (token == null) {
            Log.e(TAG, "No access token available")
            return
        }
        
        // 수동 연결이므로 재연결 시도 횟수 초기화
        isManualDisconnect = false
        reconnectAttempts = 0
        isReconnecting = false
        
        try {
            // 토큰을 URL에 쿼리 파라미터로 추가
            // 백엔드 WebSocketAuthInterceptor가 쿼리 파라미터에서 토큰을 읽음
            val urlWithToken = "$WS_URL?token=$token"
            Log.d(TAG, "Connecting to WebSocket: $urlWithToken")
            stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, urlWithToken)
            
            // Heartbeat 설정
            stompClient?.withServerHeartbeat(10000)
                ?.withClientHeartbeat(10000)
            
            val disposable = stompClient?.lifecycle()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { lifecycleEvent ->
                        try {
                            val eventType = lifecycleEvent.type
                            when {
                                eventType.name == "OPENED" -> {
                                    Log.d(TAG, "WebSocket opened")
                                    reconnectAttempts = 0 // 연결 성공 시 재시도 횟수 초기화
                                    isReconnecting = false // 재연결 완료
                                    onConnected()
                                }
                                eventType.name == "ERROR" -> {
                                    Log.e(TAG, "WebSocket error: ${lifecycleEvent.exception}")
                                    onError(lifecycleEvent.exception)
                                    // 에러 발생 시 재연결 시도 (중복 방지)
                                    Log.d(TAG, "ERROR event - isManualDisconnect=$isManualDisconnect, isReconnecting=$isReconnecting")
                                    if (!isManualDisconnect && !isReconnecting) {
                                        scheduleReconnect()
                                    } else {
                                        Log.d(TAG, "Skipping reconnect: isManualDisconnect=$isManualDisconnect, isReconnecting=$isReconnecting")
                                    }
                                }
                                eventType.name == "CLOSED" -> {
                                    Log.d(TAG, "WebSocket closed")
                                    onDisconnected()
                                    // 수동으로 끊지 않은 경우에만 재연결 시도 (중복 방지)
                                    Log.d(TAG, "CLOSED event - isManualDisconnect=$isManualDisconnect, isReconnecting=$isReconnecting")
                                    if (!isManualDisconnect && !isReconnecting) {
                                        scheduleReconnect()
                                    } else {
                                        Log.d(TAG, "Skipping reconnect: isManualDisconnect=$isManualDisconnect, isReconnecting=$isReconnecting")
                                    }
                                }
                                eventType.name == "FAILED_SERVER_HEARTBEAT" -> {
                                    Log.w(TAG, "Server heartbeat failed")
                                    // 하트비트 실패 시에도 재연결 시도
                                    if (!isManualDisconnect && stompClient?.isConnected != true) {
                                        scheduleReconnect()
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error handling lifecycle event", e)
                        }
                    },
                    { error ->
                        Log.e(TAG, "Lifecycle subscription error", error)
                        onError(error)
                        if (!isManualDisconnect && !isReconnecting) {
                            scheduleReconnect()
                        }
                    }
                )
            
            disposable?.let { disposables.add(it) }
            
            stompClient?.connect()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to connect WebSocket", e)
            if (!isManualDisconnect && !isReconnecting) {
                scheduleReconnect()
            }
        }
    }
    
    private fun scheduleReconnect() {
        // 동기화를 위해 synchronized 블록 사용
        synchronized(this) {
            if (isManualDisconnect) {
                Log.d(TAG, "Manual disconnect, skipping reconnect")
                return
            }
            
            if (isReconnecting) {
                Log.d(TAG, "Reconnect already scheduled, skipping duplicate")
                return
            }
            
            if (reconnectAttempts >= maxReconnectAttempts) {
                Log.w(TAG, "Max reconnect attempts reached ($maxReconnectAttempts). Stopping reconnection.")
                return
            }
            
            isReconnecting = true
            reconnectAttempts++
            val delay = reconnectDelayMs * reconnectAttempts // 지수 백오프
            
            Log.d(TAG, "Scheduling reconnect attempt $reconnectAttempts/$maxReconnectAttempts in ${delay}ms")
            
            // 메인 스레드에서만 타이머 사용 → 스레드 과다 생성 방지 (pthread_create OOM 방지)
            val disposable = Observable.timer(delay, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    synchronized(this@WebSocketManager) {
                        isReconnecting = false
                    }
                    if (!isManualDisconnect && stompClient?.isConnected != true) {
                        Log.d(TAG, "Attempting to reconnect...")
                        connect()
                    }
                }, { error ->
                    synchronized(this@WebSocketManager) {
                        isReconnecting = false
                    }
                    Log.e(TAG, "Failed to schedule reconnect", error)
                })
            
            disposable?.let { disposables.add(it) }
        }
    }
    
    fun disconnect() {
        try {
            // 수동으로 연결을 끊는 경우 플래그 설정
            isManualDisconnect = true
            reconnectAttempts = 0
            isReconnecting = false
            
            if (stompClient?.isConnected == true) {
                Log.d(TAG, "Disconnecting WebSocket")
                stompClient?.disconnect()
            } else {
                Log.d(TAG, "WebSocket is not connected, skipping disconnect")
            }
            disposables.clear()
            stompClient = null
        } catch (e: Exception) {
            Log.e(TAG, "Failed to disconnect WebSocket", e)
        }
    }
    
    fun subscribeToChatRoom(chatRoomId: Long, callback: (MessageResponseDto) -> Unit) {
        val destination = "/queue/chat/rooms/$chatRoomId"
        
        // 연결 상태 확인
        if (stompClient?.isConnected != true) {
            Log.w(TAG, "WebSocket is not connected. Attempting to connect...")
            
            // 연결 시도
            if (stompClient == null) {
                connect()
            }
            
            // 연결이 완료될 때까지 대기 후 구독 시도 (최대 5초 대기)
            val disposable = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(50) // 최대 5초 대기
                .map { stompClient?.isConnected == true }
                .filter { it }
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "WebSocket connected, subscribing to chat room $chatRoomId")
                        performSubscribe(chatRoomId, destination, callback)
                    },
                    { error ->
                        Log.e(TAG, "Failed to wait for connection, attempting subscribe anyway", error)
                        // 타임아웃이 발생해도 구독 시도 (연결이 완료되었을 수도 있음)
                        performSubscribe(chatRoomId, destination, callback)
                    }
                )
            
            disposable?.let { disposables.add(it) }
        } else {
            // 이미 연결되어 있으면 바로 구독
            Log.d(TAG, "WebSocket already connected, subscribing to chat room $chatRoomId")
            performSubscribe(chatRoomId, destination, callback)
        }
    }
    
    private fun performSubscribe(chatRoomId: Long, destination: String, callback: (MessageResponseDto) -> Unit) {
        if (stompClient?.isConnected != true) {
            Log.e(TAG, "Cannot subscribe: WebSocket is not connected")
            return
        }
        
        val disposable = stompClient?.topic(destination)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { message ->
                    try {
                        val messageDto = gson.fromJson(message.payload, WebSocketMessageDto::class.java)
                        val responseDto = convertToMessageResponseDto(messageDto)
                        callback(responseDto)
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to parse message", e)
                    }
                },
                { error ->
                    Log.e(TAG, "Failed to subscribe to chat room $chatRoomId", error)
                }
            )
        
        disposable?.let { disposables.add(it) }
    }
    
    fun sendMessage(chatRoomId: Long, content: String) {
        val destination = "/app/chat/rooms/$chatRoomId/messages"
        val message = mapOf(
            "chatRoomId" to chatRoomId,
            "content" to content,
            "messageType" to "TEXT"
        )
        
        try {
            stompClient?.send(destination, gson.toJson(message))
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    Log.d(TAG, "Message sent successfully")
                }, { error ->
                    Log.e(TAG, "Failed to send message", error)
                })
                ?.let { disposables.add(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send message", e)
        }
    }
    
    fun subscribeToUserNotifications(callback: (String) -> Unit) {
        val userId = TokenManager.getUserId()
        if (userId == -1L) return
        
        val destination = "/user/$userId/notifications"
        
        val disposable = stompClient?.topic(destination)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { message ->
                    try {
                        callback(message.payload)
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to handle notification", e)
                    }
                },
                { error ->
                    Log.e(TAG, "Failed to subscribe to user notifications", error)
                }
            )
        
        disposable?.let { disposables.add(it) }
    }
    
    private fun convertToMessageResponseDto(wsMessage: WebSocketMessageDto): MessageResponseDto {
        return MessageResponseDto(
            messageId = wsMessage.messageId ?: 0L,
            chatRoomId = wsMessage.chatRoomId ?: 0L,
            senderId = wsMessage.senderId ?: 0L,
            senderName = wsMessage.senderName ?: "",
            content = wsMessage.content ?: "",
            messageType = wsMessage.messageType ?: com.atolow.miixs.data.model.MessageType.TEXT,
            createdAt = wsMessage.timestamp,
            readAt = null,
            isRead = false
        )
    }
    
    private fun onConnected() {
        Log.d(TAG, "WebSocket connected")
        // 연결 성공 시 등록된 리스너들 호출
        connectionListeners.forEach { listener ->
            try {
                listener()
            } catch (e: Exception) {
                Log.e(TAG, "Error calling connection listener", e)
            }
        }
    }
    
    /**
     * 연결 성공 시 호출될 리스너 등록
     */
    fun addConnectionListener(listener: () -> Unit) {
        connectionListeners.add(listener)
        // 이미 연결되어 있으면 즉시 호출
        if (stompClient?.isConnected == true) {
            listener()
        }
    }
    
    /**
     * 연결 리스너 제거
     */
    fun removeConnectionListener(listener: () -> Unit) {
        connectionListeners.remove(listener)
    }
    
    private fun onDisconnected() {
        Log.d(TAG, "WebSocket disconnected")
    }
    
    private fun onError(exception: Throwable?) {
        Log.e(TAG, "WebSocket error", exception)
    }
    
    fun isConnected(): Boolean {
        return stompClient?.isConnected == true
    }
}


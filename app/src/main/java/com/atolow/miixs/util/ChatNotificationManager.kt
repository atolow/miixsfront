package com.atolow.miixs.util

import android.content.Context
import android.util.Log
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.data.repository.ChatRepository
import com.atolow.miixs.data.websocket.WebSocketManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * 전역 메시지 알림을 관리하는 매니저
 * 모든 채팅방의 메시지를 구독하고 알림을 표시합니다.
 */
object ChatNotificationManager {
    private const val TAG = "ChatNotificationManager"
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    // 현재 열려있는 채팅방 ID (이 채팅방의 메시지는 알림 표시 안 함)
    @Volatile
    private var currentChatRoomId: Long? = null
    
    // 구독한 채팅방 목록
    private val subscribedRooms = mutableSetOf<Long>()
    
    // 현재 컨텍스트 (알림 표시용)
    @Volatile
    private var currentContext: Context? = null
    
    // 연결 리스너가 등록되었는지 여부
    @Volatile
    private var connectionListenerRegistered = false
    
    // 메시지 수신 리스너 (채팅 목록 새로고침용)
    private val messageReceivedListeners = mutableListOf<(Long) -> Unit>() // chatRoomId를 전달
    
    /**
     * 현재 열려있는 채팅방 ID 설정
     */
    fun setCurrentChatRoomId(chatRoomId: Long?) {
        currentChatRoomId = chatRoomId
        Log.d(TAG, "현재 채팅방 ID 설정: $chatRoomId")
    }
    
    /**
     * 메시지 수신 리스너 추가
     */
    fun addMessageReceivedListener(listener: (Long) -> Unit) {
        messageReceivedListeners.add(listener)
        Log.d(TAG, "메시지 수신 리스너 추가됨. 총 ${messageReceivedListeners.size}개")
    }
    
    /**
     * 메시지 수신 리스너 제거
     */
    fun removeMessageReceivedListener(listener: (Long) -> Unit) {
        messageReceivedListeners.remove(listener)
        Log.d(TAG, "메시지 수신 리스너 제거됨. 총 ${messageReceivedListeners.size}개")
    }
    
    /**
     * 모든 채팅방에 대한 메시지 구독 시작
     */
    fun startListening(context: Context) {
        if (!TokenManager.isLoggedIn()) {
            Log.w(TAG, "로그인되지 않아 알림 구독을 시작할 수 없습니다")
            return
        }
        
        currentContext = context
        
        // 연결 리스너가 아직 등록되지 않았으면 등록
        if (!connectionListenerRegistered) {
            WebSocketManager.getInstance().addConnectionListener {
                Log.d(TAG, "WebSocket 연결 성공, 알림 구독 시작")
                currentContext?.let { ctx ->
                    loadAndSubscribeToAllChatRooms(ctx)
                }
            }
            connectionListenerRegistered = true
        }
        
        // WebSocket 연결 확인
        if (!WebSocketManager.getInstance().isConnected()) {
            Log.w(TAG, "WebSocket이 연결되지 않음. 연결 시도 중...")
            // WebSocket 연결 시도
            WebSocketManager.getInstance().connect()
            return
        }
        
        // 이미 연결되어 있으면 바로 구독 시작
        loadAndSubscribeToAllChatRooms(context)
    }
    
    /**
     * 채팅방 목록을 로드하고 모든 채팅방에 구독
     */
    private fun loadAndSubscribeToAllChatRooms(context: Context) {
        scope.launch {
            try {
                val chatRepository = ChatRepository()
                // 채팅방 목록 가져오기
                chatRepository.getChatRoomList().fold(
                    onSuccess = { pageResponse ->
                        val chatRooms = pageResponse.content
                        Log.d(TAG, "채팅방 목록 로드 완료: ${chatRooms.size}개")
                        
                        // 각 채팅방에 대해 메시지 구독
                        chatRooms.forEach { chatRoom ->
                            subscribeToChatRoom(context, chatRoom.chatRoomId, chatRoom.otherUserName)
                        }
                    },
                    onFailure = { e ->
                        Log.e(TAG, "채팅방 목록 로드 실패", e)
                    }
                )
            } catch (e: Exception) {
                Log.e(TAG, "알림 구독 시작 실패", e)
            }
        }
    }
    
    /**
     * 채팅방 목록 새로고침 (새 채팅방이 추가되었을 때)
     */
    fun refreshSubscriptions(context: Context) {
        // 기존 구독은 유지하고, 새로운 채팅방만 추가 구독
        loadAndSubscribeToAllChatRooms(context)
    }
    
    /**
     * 특정 채팅방에 대한 메시지 구독
     */
    private fun subscribeToChatRoom(context: Context, chatRoomId: Long, otherUserName: String) {
        if (subscribedRooms.contains(chatRoomId)) {
            Log.d(TAG, "이미 구독 중인 채팅방: $chatRoomId")
            return
        }
        
        subscribedRooms.add(chatRoomId)
        
        WebSocketManager.getInstance().subscribeToChatRoom(chatRoomId) { message ->
            // 현재 열려있는 채팅방이 아니면 알림 표시
            if (currentChatRoomId != chatRoomId) {
                // 자신이 보낸 메시지가 아니면 알림 표시
                val currentUserId = TokenManager.getUserId()
                if (message.senderId != currentUserId) {
                    Log.d(TAG, "새 메시지 수신: chatRoomId=$chatRoomId, sender=${message.senderName}, content=${message.content}, type=${message.messageType}")
                    NotificationHelper.showMessageNotification(
                        context = context,
                        chatRoomId = chatRoomId,
                        senderName = message.senderName,
                        message = message.content,
                        messageType = message.messageType,
                        chatRoomTitle = otherUserName
                    )
                    
                    // 메시지 수신 리스너 호출 (채팅 목록 새로고침용)
                    messageReceivedListeners.forEach { listener ->
                        try {
                            listener(chatRoomId)
                        } catch (e: Exception) {
                            Log.e(TAG, "메시지 수신 리스너 호출 실패", e)
                        }
                    }
                }
            } else {
                Log.d(TAG, "현재 열려있는 채팅방이므로 알림 표시 안 함: $chatRoomId")
            }
        }
        
        Log.d(TAG, "채팅방 구독 완료: $chatRoomId")
    }
    
    /**
     * 특정 채팅방 구독 추가 (새 채팅방이 생성되었을 때)
     */
    fun subscribeToNewChatRoom(context: Context, chatRoomId: Long, otherUserName: String) {
        subscribeToChatRoom(context, chatRoomId, otherUserName)
    }
    
    /**
     * 모든 구독 해제
     */
    fun stopListening() {
        subscribedRooms.clear()
        currentChatRoomId = null
        Log.d(TAG, "모든 채팅방 구독 해제")
    }
}


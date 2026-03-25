package com.atolow.miixs.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.ui.main.MainActivity
import com.atolow.miixs.util.ChatNotificationManager

class ChatNotificationService : Service() {
    private val TAG = "ChatNotificationService"
    private val NOTIFICATION_ID = 1
    private val CHANNEL_ID = "chat_notification_service_channel"
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.d(TAG, "ChatNotificationService created")
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "ChatNotificationService started")
        
        // Foreground Service로 시작 (알림은 필수이지만 IMPORTANCE_NONE으로 완전히 숨김)
        val notification = createNotification()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10 이상에서는 foregroundServiceType 필요
            startForeground(NOTIFICATION_ID, notification, android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
        
        // IMPORTANCE_NONE 채널을 사용하면 알림이 자동으로 숨겨지지만,
        // 일부 기기(특히 삼성 One UI)에서는 여전히 표시될 수 있음
        // stopForeground(STOP_FOREGROUND_REMOVE)를 사용하여 알림 제거
        // 서비스는 계속 실행되며 WebSocket 연결도 유지됨
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            try {
                // stopForeground(STOP_FOREGROUND_REMOVE): 알림만 제거하고 서비스는 계속 실행
                // 이렇게 하면 Foreground Service 알림은 사라지지만 서비스는 백그라운드에서 계속 실행됨
                stopForeground(Service.STOP_FOREGROUND_REMOVE)
                Log.d(TAG, "Foreground Service 알림 제거 완료 (서비스는 계속 실행됨)")
                
                // 추가로 NotificationManager를 통해서도 취소 시도
                val notificationManager = getSystemService(NotificationManager::class.java)
                notificationManager.cancel(NOTIFICATION_ID)
            } catch (e: Exception) {
                Log.e(TAG, "알림 제거 실패: ${e.message}")
            }
        }, 100) // 100ms 후 제거 (Foreground Service 시작 후 약간의 지연 필요)
        
        // 알림 구독 시작
        if (TokenManager.isLoggedIn()) {
            ChatNotificationManager.startListening(this)
        }
        
        // 서비스가 종료되어도 자동으로 재시작
        return START_STICKY
    }
    
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ChatNotificationService destroyed")
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            
            // 기존 채널이 있으면 삭제 (중요도 변경을 위해)
            try {
                notificationManager.deleteNotificationChannel(CHANNEL_ID)
                Log.d(TAG, "기존 알림 채널 삭제 완료")
            } catch (e: Exception) {
                Log.d(TAG, "기존 알림 채널이 없거나 삭제 실패: ${e.message}")
            }
            
            // IMPORTANCE_NONE으로 설정하여 알림이 완전히 숨겨지도록 함
            // Android 8.0 (API 26) 이상에서는 IMPORTANCE_NONE이 알림을 완전히 숨깁니다
            // 상태 표시줄, 알림 패널 모두에 표시되지 않음 (카카오톡과 동일)
            val importance = NotificationManager.IMPORTANCE_NONE
            
            val channel = NotificationChannel(
                CHANNEL_ID,
                "채팅 알림 서비스",
                importance
            ).apply {
                description = "백그라운드에서 메시지 알림을 받기 위한 서비스"
                setShowBadge(false) // 배지 표시 안 함
                enableVibration(false) // 진동 비활성화
                enableLights(false) // LED 비활성화
                setSound(null, null) // 소리 비활성화
                // 알림을 완전히 숨기기 위한 추가 설정
                lockscreenVisibility = Notification.VISIBILITY_SECRET // 잠금 화면에서 숨김
            }
            
            notificationManager.createNotificationChannel(channel)
            Log.d(TAG, "IMPORTANCE_NONE 알림 채널 생성 완료")
        }
    }
    
    private fun createNotification(): Notification {
        // IMPORTANCE_NONE 채널을 사용하면 알림이 완전히 숨겨지므로
        // 내용은 최소한으로 설정 (Foreground Service 요구사항 충족용)
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // IMPORTANCE_NONE 채널을 사용하면 알림이 완전히 숨겨짐
        // 상태 표시줄, 알림 패널 모두에 표시되지 않음 (카카오톡과 동일)
        // 제목과 내용을 완전히 빈 문자열로 설정
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("") // 완전히 빈 제목
            .setContentText("") // 완전히 빈 내용
            .setSmallIcon(com.atolow.miixs.R.drawable.ic_transparent) // 투명한 아이콘
            .setContentIntent(pendingIntent)
            .setOngoing(true) // Foreground Service 요구사항
            .setPriority(NotificationCompat.PRIORITY_MIN) // 최소 우선순위
            .setSilent(true) // 소리와 진동 없음
            .setShowWhen(false) // 시간 표시 안 함
            .setCategory(NotificationCompat.CATEGORY_SERVICE) // 서비스 카테고리
            .setVisibility(NotificationCompat.VISIBILITY_SECRET) // 잠금 화면에서 숨김
            .setLocalOnly(true) // 로컬에서만 표시
        
        // Android 8.0 이상에서 추가 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID)
        }
        
        // Android 14 (API 34) 이상에서는 Foreground Service Behavior 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            builder.setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
        }
        
        return builder.build()
    }
}


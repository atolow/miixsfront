package com.atolow.miixs.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.atolow.miixs.R
import com.atolow.miixs.ui.chat.ChatRoomActivity
import com.atolow.miixs.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object NotificationHelper {
    private const val CHANNEL_ID = "miixs_chat_channel"
    private const val CHANNEL_NAME = "채팅 알림"
    private const val CHANNEL_DESCRIPTION = "새로운 메시지 알림"
    
    private var notificationId = 0
    
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH // 높은 중요도로 헤드업 알림(팝업) 활성화
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableVibration(true) // 진동 활성화
                enableLights(true) // LED 표시등 활성화
                // 헤드업 알림(팝업) 표시를 위한 추가 설정
                setShowBadge(true) // 배지 표시
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    fun showMessageNotification(
        context: Context,
        chatRoomId: Long,
        senderName: String,
        message: String,
        messageType: com.atolow.miixs.data.model.MessageType,
        chatRoomTitle: String? = null
    ) {
        // 푸시알림 설정 확인
        com.atolow.miixs.data.local.NotificationSettings.init(context)
        if (!com.atolow.miixs.data.local.NotificationSettings.isPushNotificationEnabled()) {
            android.util.Log.d("NotificationHelper", "푸시알림이 사용자에 의해 비활성화되었습니다")
            return
        }
        
        // 알림 권한 확인 (Android 13 이상)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (!notificationManager.areNotificationsEnabled()) {
                android.util.Log.w("NotificationHelper", "알림이 사용자에 의해 비활성화되었습니다")
                return
            }
        }
        
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // 채팅방으로 이동하는 Intent
        val intent = Intent(context, ChatRoomActivity::class.java).apply {
            putExtra("chatRoomId", chatRoomId)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            chatRoomId.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // 알림 제목 (채팅방 제목 또는 보낸 사람 이름) - 첫 번째 줄
        val title = chatRoomTitle ?: senderName
        
        // 현재 시간 포맷팅 (오후/오전 HH:mm)
        val currentTime = SimpleDateFormat("a hh:mm", Locale.getDefault()).format(Date())
        
        // 두 번째 줄: "메세지 알림"
        val subtitle = "메세지 알림"
        
        // 세 번째 줄: 모든 경우에 동일하게 "새로운 메세지가 도착했습니다"
        val messageText = "새로운 메세지가 도착했습니다"
        
        // 알림 내용 (두 번째 줄 + 세 번째 줄)
        val contentText = "$subtitle\n$messageText"
        
        // 각 채팅방마다 고유한 알림 ID 사용 (같은 채팅방의 알림은 항상 덮어씀)
        val notificationIdForRoom = chatRoomId.toInt()
        val tag = "chat_room_$chatRoomId" // 태그 추가
        
        // 알림 표시 전 활성 알림 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNotificationsBefore = notificationManager.activeNotifications
            android.util.Log.d("NotificationHelper", "=== 알림 표시 전 ===")
            android.util.Log.d("NotificationHelper", "활성 알림 개수: ${activeNotificationsBefore.size}")
            activeNotificationsBefore.forEachIndexed { index, statusBarNotification ->
                android.util.Log.d("NotificationHelper", "알림[$index]: tag=${statusBarNotification.tag}, id=${statusBarNotification.id}, channelId=${statusBarNotification.notification.channelId}")
            }
        }
        
        // 알림 표시 전에 기존 알림 취소 (같은 채팅방의 이전 알림 제거)
        // cancelAllNotificationsForChatRoom을 호출하여 모든 알림을 확실히 취소
        cancelAllNotificationsForChatRoom(context, chatRoomId)
        
        // 알림 취소 후 활성 알림 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNotificationsAfterCancel = notificationManager.activeNotifications
            android.util.Log.d("NotificationHelper", "=== 알림 취소 후 ===")
            android.util.Log.d("NotificationHelper", "활성 알림 개수: ${activeNotificationsAfterCancel.size}")
            activeNotificationsAfterCancel.forEachIndexed { index, statusBarNotification ->
                android.util.Log.d("NotificationHelper", "알림[$index]: tag=${statusBarNotification.tag}, id=${statusBarNotification.id}, channelId=${statusBarNotification.notification.channelId}")
            }
        }
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // 기본 시스템 아이콘 사용
            .setContentTitle(title) // 첫 번째 줄: 채팅방 이름 (시간은 시스템이 자동 추가)
            .setContentText(contentText) // 두 번째 줄: "메세지 알림"
            .setStyle(NotificationCompat.BigTextStyle()
                .setBigContentTitle(title) // 확장 시 제목
                .bigText(contentText)) // 확장 시 전체 텍스트
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 높은 우선순위로 헤드업 알림(팝업) 표시
            .setDefaults(NotificationCompat.DEFAULT_ALL) // 소리, 진동, LED 모두 활성화
            .setAutoCancel(true) // 알림 클릭 시 자동으로 제거
            .setContentIntent(pendingIntent) // 알림 클릭 시 채팅방으로 이동
            .setFullScreenIntent(pendingIntent, false) // 전체 화면 인텐트는 사용하지 않음
            .build()
        
        // Android 7.0 이상에서는 태그를 사용할 수 있음
        // 같은 태그와 ID를 사용하면 항상 덮어씁니다
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationManager.notify(tag, notificationIdForRoom, notification)
        } else {
            notificationManager.notify(notificationIdForRoom, notification)
        }
        
        // 알림 표시 후 활성 알림 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNotificationsAfterNotify = notificationManager.activeNotifications
            android.util.Log.d("NotificationHelper", "=== 알림 표시 후 ===")
            android.util.Log.d("NotificationHelper", "활성 알림 개수: ${activeNotificationsAfterNotify.size}")
            activeNotificationsAfterNotify.forEachIndexed { index, statusBarNotification ->
                android.util.Log.d("NotificationHelper", "알림[$index]: tag=${statusBarNotification.tag}, id=${statusBarNotification.id}, channelId=${statusBarNotification.notification.channelId}")
            }
        }
        
        android.util.Log.d("NotificationHelper", "알림 표시 완료: chatRoomId=$chatRoomId, notificationId=$notificationIdForRoom, tag=$tag")
    }
    
    fun cancelNotification(context: Context, chatRoomId: Long) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 해당 채팅방의 알림 ID로 취소
        notificationManager.cancel(chatRoomId.toInt())
        
        // Android에서 알림이 그룹화되어 있을 수 있으므로, 
        // 해당 채팅방과 관련된 모든 알림을 찾아서 취소
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNotifications = notificationManager.activeNotifications
            activeNotifications.forEach { statusBarNotification ->
                // 알림의 태그나 추가 데이터를 확인하여 같은 채팅방의 알림인지 확인
                val notification = statusBarNotification.notification
                val extras = notification.extras
                
                // PendingIntent에서 chatRoomId를 추출할 수 없으므로,
                // 알림 ID가 같은 채팅방 범위 내에 있는지 확인
                // 하지만 정확한 방법은 알림에 chatRoomId를 태그로 저장하는 것
                // 일단 같은 ID의 알림만 취소 (이미 위에서 취소함)
            }
        }
    }
    
    /**
     * 특정 채팅방의 모든 알림을 취소
     * 채팅방 진입 시 호출하여 해당 채팅방의 모든 알림을 지움
     */
    fun cancelAllNotificationsForChatRoom(context: Context, chatRoomId: Long) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val tag = "chat_room_$chatRoomId"
        val notificationId = chatRoomId.toInt()
        
        android.util.Log.d("NotificationHelper", "=== 채팅방 $chatRoomId 의 알림 취소 시작 ===")
        android.util.Log.d("NotificationHelper", "찾을 태그: $tag, 찾을 ID: $notificationId")
        
        // Android 23 이상에서는 활성 알림 목록을 먼저 확인하여 같은 채팅방의 모든 알림 취소
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNotifications = notificationManager.activeNotifications
            android.util.Log.d("NotificationHelper", "취소 전 활성 알림 개수: ${activeNotifications.size}")
            
            // 모든 활성 알림 상세 정보 로그
            activeNotifications.forEachIndexed { index, statusBarNotification ->
                val notificationTag = statusBarNotification.tag
                val notificationIdValue = statusBarNotification.id
                android.util.Log.d("NotificationHelper", "활성 알림[$index]: tag=$notificationTag, id=$notificationIdValue, channelId=${statusBarNotification.notification.channelId}")
            }
            
            // 먼저 모든 활성 알림을 확인하여 같은 채팅방의 알림 찾기
            val notificationsToCancel = mutableListOf<Pair<String?, Int>>()
            
            activeNotifications.forEach { statusBarNotification ->
                val notificationTag = statusBarNotification.tag
                val notificationIdValue = statusBarNotification.id
                val channelId = statusBarNotification.notification.channelId
                
                // Foreground Service 알림은 제외 (chat_notification_service_channel)
                if (channelId == "chat_notification_service_channel") {
                    android.util.Log.d("NotificationHelper", "✗ Foreground Service 알림은 제외: tag=$notificationTag, id=$notificationIdValue, channelId=$channelId")
                    return@forEach
                }
                
                // 같은 태그를 가진 알림이거나, 같은 ID를 가진 알림 찾기
                val shouldCancel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    notificationTag == tag || notificationIdValue == notificationId
                } else {
                    notificationIdValue == notificationId
                }
                
                if (shouldCancel) {
                    notificationsToCancel.add(Pair(notificationTag, notificationIdValue))
                    android.util.Log.d("NotificationHelper", "✓ 취소할 알림 발견: tag=$notificationTag, id=$notificationIdValue, channelId=$channelId")
                } else {
                    android.util.Log.d("NotificationHelper", "✗ 취소하지 않음: tag=$notificationTag, id=$notificationIdValue, channelId=$channelId (찾는 tag=$tag, 찾는 id=$notificationId)")
                }
            }
            
            android.util.Log.d("NotificationHelper", "총 ${notificationsToCancel.size}개의 알림을 취소합니다")
            
            // 찾은 모든 알림 취소
            notificationsToCancel.forEach { (notificationTag, notificationIdValue) ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    notificationManager.cancel(notificationTag ?: tag, notificationIdValue)
                    android.util.Log.d("NotificationHelper", "✓ 알림 취소 완료: tag=${notificationTag ?: tag}, id=$notificationIdValue")
                } else {
                    notificationManager.cancel(notificationIdValue)
                    android.util.Log.d("NotificationHelper", "✓ 알림 취소 완료: id=$notificationIdValue")
                }
            }
            
            // 취소 후 활성 알림 확인
            val activeNotificationsAfterCancel = notificationManager.activeNotifications
            android.util.Log.d("NotificationHelper", "취소 후 활성 알림 개수: ${activeNotificationsAfterCancel.size}")
            activeNotificationsAfterCancel.forEachIndexed { index, statusBarNotification ->
                val notificationTag = statusBarNotification.tag
                val notificationIdValue = statusBarNotification.id
                android.util.Log.d("NotificationHelper", "남은 알림[$index]: tag=$notificationTag, id=$notificationIdValue, channelId=${statusBarNotification.notification.channelId}")
            }
        }
        
        // 추가로 태그와 ID를 사용하여 기본 알림 취소 (혹시 모를 경우를 대비)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationManager.cancel(tag, notificationId)
            android.util.Log.d("NotificationHelper", "추가 취소 시도: tag=$tag, id=$notificationId")
        } else {
            notificationManager.cancel(notificationId)
            android.util.Log.d("NotificationHelper", "추가 취소 시도: id=$notificationId")
        }
        
        android.util.Log.d("NotificationHelper", "=== 채팅방 $chatRoomId 의 알림 취소 완료 ===")
    }
    
    fun cancelAllNotifications(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }
}


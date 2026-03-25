package com.atolow.miixs.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.atolow.miixs.R;
import com.atolow.miixs.ui.chat.ChatRoomActivity;
import com.atolow.miixs.ui.main.MainActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ:\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/atolow/miixs/util/NotificationHelper;", "", "()V", "CHANNEL_DESCRIPTION", "", "CHANNEL_ID", "CHANNEL_NAME", "notificationId", "", "cancelAllNotifications", "", "context", "Landroid/content/Context;", "cancelAllNotificationsForChatRoom", "chatRoomId", "", "cancelNotification", "createNotificationChannel", "showMessageNotification", "senderName", "message", "messageType", "Lcom/atolow/miixs/data/model/MessageType;", "chatRoomTitle", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "miixs_chat_channel";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "\ucc44\ud305 \uc54c\ub9bc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_DESCRIPTION = "\uc0c8\ub85c\uc6b4 \uba54\uc2dc\uc9c0 \uc54c\ub9bc";
    private static int notificationId = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.util.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createNotificationChannel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void showMessageNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long chatRoomId, @org.jetbrains.annotations.NotNull()
    java.lang.String senderName, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.MessageType messageType, @org.jetbrains.annotations.Nullable()
    java.lang.String chatRoomTitle) {
    }
    
    public final void cancelNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long chatRoomId) {
    }
    
    /**
     * 특정 채팅방의 모든 알림을 취소
     * 채팅방 진입 시 호출하여 해당 채팅방의 모든 알림을 지움
     */
    public final void cancelAllNotificationsForChatRoom(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long chatRoomId) {
    }
    
    public final void cancelAllNotifications(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}
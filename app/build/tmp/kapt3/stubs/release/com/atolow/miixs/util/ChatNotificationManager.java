package com.atolow.miixs.util;

import android.content.Context;
import android.util.Log;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.data.repository.ChatRepository;
import com.atolow.miixs.data.websocket.WebSocketManager;
import kotlinx.coroutines.Dispatchers;

/**
 * 전역 메시지 알림을 관리하는 매니저
 * 모든 채팅방의 메시지를 구독하고 알림을 표시합니다.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0014\u001a\u00020\u000f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J\u000e\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000bJ\u001a\u0010\u0019\u001a\u00020\u000f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0015\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000bJ\u0006\u0010\u001e\u001a\u00020\u000fJ \u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0004H\u0002J\u001e\u0010!\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/atolow/miixs/util/ChatNotificationManager;", "", "()V", "TAG", "", "connectionListenerRegistered", "", "currentChatRoomId", "", "Ljava/lang/Long;", "currentContext", "Landroid/content/Context;", "messageReceivedListeners", "", "Lkotlin/Function1;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "subscribedRooms", "", "addMessageReceivedListener", "listener", "loadAndSubscribeToAllChatRooms", "context", "refreshSubscriptions", "removeMessageReceivedListener", "setCurrentChatRoomId", "chatRoomId", "(Ljava/lang/Long;)V", "startListening", "stopListening", "subscribeToChatRoom", "otherUserName", "subscribeToNewChatRoom", "app_release"})
public final class ChatNotificationManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ChatNotificationManager";
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.CoroutineScope scope = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile java.lang.Long currentChatRoomId;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.Long> subscribedRooms = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile android.content.Context currentContext;
    @kotlin.jvm.Volatile()
    private static volatile boolean connectionListenerRegistered = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<kotlin.jvm.functions.Function1<java.lang.Long, kotlin.Unit>> messageReceivedListeners = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.util.ChatNotificationManager INSTANCE = null;
    
    private ChatNotificationManager() {
        super();
    }
    
    /**
     * 현재 열려있는 채팅방 ID 설정
     */
    public final void setCurrentChatRoomId(@org.jetbrains.annotations.Nullable()
    java.lang.Long chatRoomId) {
    }
    
    /**
     * 메시지 수신 리스너 추가
     */
    public final void addMessageReceivedListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> listener) {
    }
    
    /**
     * 메시지 수신 리스너 제거
     */
    public final void removeMessageReceivedListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> listener) {
    }
    
    /**
     * 모든 채팅방에 대한 메시지 구독 시작
     */
    public final void startListening(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * 채팅방 목록을 로드하고 모든 채팅방에 구독
     */
    private final void loadAndSubscribeToAllChatRooms(android.content.Context context) {
    }
    
    /**
     * 채팅방 목록 새로고침 (새 채팅방이 추가되었을 때)
     */
    public final void refreshSubscriptions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * 특정 채팅방에 대한 메시지 구독
     */
    private final void subscribeToChatRoom(android.content.Context context, long chatRoomId, java.lang.String otherUserName) {
    }
    
    /**
     * 특정 채팅방 구독 추가 (새 채팅방이 생성되었을 때)
     */
    public final void subscribeToNewChatRoom(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long chatRoomId, @org.jetbrains.annotations.NotNull()
    java.lang.String otherUserName) {
    }
    
    /**
     * 모든 구독 해제
     */
    public final void stopListening() {
    }
}
package com.atolow.miixs.data.websocket;

import android.util.Log;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.data.model.chat.WebSocketMessageDto;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\fJ\b\u0010\u001e\u001a\u00020\u0006H\u0002J\b\u0010\u001f\u001a\u00020\u0006H\u0002J\u0012\u0010 \u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J,\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\u0012\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060(H\u0002J\u0014\u0010)\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\b\u0010*\u001a\u00020\u0006H\u0002J\u0016\u0010+\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00122\u0006\u0010,\u001a\u00020&J\"\u0010-\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00122\u0012\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060(J\u001a\u0010.\u001a\u00020\u00062\u0012\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00060(R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/atolow/miixs/data/websocket/WebSocketManager;", "", "()V", "connectionListeners", "", "Lkotlin/Function0;", "", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "gson", "Lcom/google/gson/Gson;", "isManualDisconnect", "", "isReconnecting", "maxReconnectAttempts", "", "reconnectAttempts", "reconnectDelayMs", "", "stompClient", "Lua/naiksoftware/stomp/StompClient;", "addConnectionListener", "listener", "connect", "convertToMessageResponseDto", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "wsMessage", "Lcom/atolow/miixs/data/model/chat/WebSocketMessageDto;", "disconnect", "isConnected", "onConnected", "onDisconnected", "onError", "exception", "", "performSubscribe", "chatRoomId", "destination", "", "callback", "Lkotlin/Function1;", "removeConnectionListener", "scheduleReconnect", "sendMessage", "content", "subscribeToChatRoom", "subscribeToUserNotifications", "Companion", "app_debug"})
public final class WebSocketManager {
    @org.jetbrains.annotations.Nullable()
    private ua.naiksoftware.stomp.StompClient stompClient;
    @org.jetbrains.annotations.NotNull()
    private final io.reactivex.disposables.CompositeDisposable disposables = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    private boolean isManualDisconnect = false;
    private int reconnectAttempts = 0;
    private final int maxReconnectAttempts = 5;
    private final long reconnectDelayMs = 3000L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.jvm.functions.Function0<kotlin.Unit>> connectionListeners = null;
    @kotlin.jvm.Volatile()
    private volatile boolean isReconnecting = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "WebSocketManager";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String WS_URL = "wss://miixs.com/ws";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.atolow.miixs.data.websocket.WebSocketManager INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.websocket.WebSocketManager.Companion Companion = null;
    
    private WebSocketManager() {
        super();
    }
    
    public final void connect() {
    }
    
    private final void scheduleReconnect() {
    }
    
    public final void disconnect() {
    }
    
    public final void subscribeToChatRoom(long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.MessageResponseDto, kotlin.Unit> callback) {
    }
    
    private final void performSubscribe(long chatRoomId, java.lang.String destination, kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.MessageResponseDto, kotlin.Unit> callback) {
    }
    
    public final void sendMessage(long chatRoomId, @org.jetbrains.annotations.NotNull()
    java.lang.String content) {
    }
    
    public final void subscribeToUserNotifications(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final com.atolow.miixs.data.model.chat.MessageResponseDto convertToMessageResponseDto(com.atolow.miixs.data.model.chat.WebSocketMessageDto wsMessage) {
        return null;
    }
    
    private final void onConnected() {
    }
    
    /**
     * 연결 성공 시 호출될 리스너 등록
     */
    public final void addConnectionListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listener) {
    }
    
    /**
     * 연결 리스너 제거
     */
    public final void removeConnectionListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listener) {
    }
    
    private final void onDisconnected() {
    }
    
    private final void onError(java.lang.Throwable exception) {
    }
    
    public final boolean isConnected() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/data/websocket/WebSocketManager$Companion;", "", "()V", "INSTANCE", "Lcom/atolow/miixs/data/websocket/WebSocketManager;", "TAG", "", "WS_URL", "getInstance", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.data.websocket.WebSocketManager getInstance() {
            return null;
        }
    }
}
package com.atolow.miixs.data.model.chat;

import com.atolow.miixs.data.model.MessageType;
import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003Jb\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0006H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0012\u0010\u000eR\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0015\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006\'"}, d2 = {"Lcom/atolow/miixs/data/model/chat/WebSocketMessageDto;", "", "chatRoomId", "", "senderId", "senderName", "", "content", "messageType", "Lcom/atolow/miixs/data/model/MessageType;", "messageId", "timestamp", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/MessageType;Ljava/lang/Long;Ljava/lang/String;)V", "getChatRoomId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getContent", "()Ljava/lang/String;", "getMessageId", "getMessageType", "()Lcom/atolow/miixs/data/model/MessageType;", "getSenderId", "getSenderName", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/MessageType;Ljava/lang/Long;Ljava/lang/String;)Lcom/atolow/miixs/data/model/chat/WebSocketMessageDto;", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class WebSocketMessageDto {
    @com.google.gson.annotations.SerializedName(value = "chatRoomId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long chatRoomId = null;
    @com.google.gson.annotations.SerializedName(value = "senderId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long senderId = null;
    @com.google.gson.annotations.SerializedName(value = "senderName")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String senderName = null;
    @com.google.gson.annotations.SerializedName(value = "content")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String content = null;
    @com.google.gson.annotations.SerializedName(value = "messageType")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.MessageType messageType = null;
    @com.google.gson.annotations.SerializedName(value = "messageId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long messageId = null;
    @com.google.gson.annotations.SerializedName(value = "timestamp")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String timestamp = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.MessageType component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.model.chat.WebSocketMessageDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long chatRoomId, @org.jetbrains.annotations.Nullable()
    java.lang.Long senderId, @org.jetbrains.annotations.Nullable()
    java.lang.String senderName, @org.jetbrains.annotations.Nullable()
    java.lang.String content, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.MessageType messageType, @org.jetbrains.annotations.Nullable()
    java.lang.Long messageId, @org.jetbrains.annotations.Nullable()
    java.lang.String timestamp) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public WebSocketMessageDto(@org.jetbrains.annotations.Nullable()
    java.lang.Long chatRoomId, @org.jetbrains.annotations.Nullable()
    java.lang.Long senderId, @org.jetbrains.annotations.Nullable()
    java.lang.String senderName, @org.jetbrains.annotations.Nullable()
    java.lang.String content, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.MessageType messageType, @org.jetbrains.annotations.Nullable()
    java.lang.Long messageId, @org.jetbrains.annotations.Nullable()
    java.lang.String timestamp) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getChatRoomId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSenderId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSenderName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getContent() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.MessageType getMessageType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getMessageId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTimestamp() {
        return null;
    }
}
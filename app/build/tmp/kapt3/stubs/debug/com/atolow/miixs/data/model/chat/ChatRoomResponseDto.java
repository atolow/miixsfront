package com.atolow.miixs.data.model.chat;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\rH\u00c6\u0003J\u008d\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010-\u001a\u00020\r2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u000200H\u00d6\u0001J\t\u00101\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0017R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013\u00a8\u00062"}, d2 = {"Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "", "chatRoomId", "", "otherUserId", "otherUserName", "", "otherUserUsername", "otherUserProfileImageUrl", "lastMessage", "lastMessageAt", "unreadCount", "deleted", "", "isFavorite", "createdAt", "modifiedAt", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZZLjava/lang/String;Ljava/lang/String;)V", "getChatRoomId", "()J", "getCreatedAt", "()Ljava/lang/String;", "getDeleted", "()Z", "getLastMessage", "getLastMessageAt", "getModifiedAt", "getOtherUserId", "getOtherUserName", "getOtherUserProfileImageUrl", "getOtherUserUsername", "getUnreadCount", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ChatRoomResponseDto {
    @com.google.gson.annotations.SerializedName(value = "chatRoomId")
    private final long chatRoomId = 0L;
    @com.google.gson.annotations.SerializedName(value = "otherUserId")
    private final long otherUserId = 0L;
    @com.google.gson.annotations.SerializedName(value = "otherUserName")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String otherUserName = null;
    @com.google.gson.annotations.SerializedName(value = "otherUserUsername")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String otherUserUsername = null;
    @com.google.gson.annotations.SerializedName(value = "otherUserProfileImageUrl")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String otherUserProfileImageUrl = null;
    @com.google.gson.annotations.SerializedName(value = "lastMessage")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastMessage = null;
    @com.google.gson.annotations.SerializedName(value = "lastMessageAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastMessageAt = null;
    @com.google.gson.annotations.SerializedName(value = "unreadCount")
    private final long unreadCount = 0L;
    @com.google.gson.annotations.SerializedName(value = "deleted")
    private final boolean deleted = false;
    @com.google.gson.annotations.SerializedName(value = "favorite")
    private final boolean isFavorite = false;
    @com.google.gson.annotations.SerializedName(value = "createdAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createdAt = null;
    @com.google.gson.annotations.SerializedName(value = "modifiedAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String modifiedAt = null;
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.model.chat.ChatRoomResponseDto copy(long chatRoomId, long otherUserId, @org.jetbrains.annotations.NotNull()
    java.lang.String otherUserName, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserUsername, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserProfileImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String lastMessage, @org.jetbrains.annotations.Nullable()
    java.lang.String lastMessageAt, long unreadCount, boolean deleted, boolean isFavorite, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
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
    
    public ChatRoomResponseDto(long chatRoomId, long otherUserId, @org.jetbrains.annotations.NotNull()
    java.lang.String otherUserName, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserUsername, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserProfileImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String lastMessage, @org.jetbrains.annotations.Nullable()
    java.lang.String lastMessageAt, long unreadCount, boolean deleted, boolean isFavorite, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
        super();
    }
    
    public final long getChatRoomId() {
        return 0L;
    }
    
    public final long getOtherUserId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOtherUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtherUserUsername() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtherUserProfileImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastMessageAt() {
        return null;
    }
    
    public final long getUnreadCount() {
        return 0L;
    }
    
    public final boolean getDeleted() {
        return false;
    }
    
    public final boolean isFavorite() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getModifiedAt() {
        return null;
    }
}
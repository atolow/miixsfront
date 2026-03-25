package com.atolow.miixs.data.model.chat

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChatRoomResponseDto(
    @SerializedName("chatRoomId")
    val chatRoomId: Long,
    @SerializedName("otherUserId")
    val otherUserId: Long,
    @SerializedName("otherUserName")
    val otherUserName: String,
    @SerializedName("otherUserUsername")
    val otherUserUsername: String?,
    @SerializedName("otherUserProfileImageUrl")
    val otherUserProfileImageUrl: String?,
    @SerializedName("lastMessage")
    val lastMessage: String?,
    @SerializedName("lastMessageAt")
    val lastMessageAt: String?,
    @SerializedName("unreadCount")
    val unreadCount: Long,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("favorite")
    val isFavorite: Boolean,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("modifiedAt")
    val modifiedAt: String?
)


package com.atolow.miixs.data.model.chat

import com.atolow.miixs.data.model.MessageType
import com.google.gson.annotations.SerializedName

data class MessageResponseDto(
    @SerializedName("messageId")
    val messageId: Long,
    @SerializedName("chatRoomId")
    val chatRoomId: Long,
    @SerializedName("senderId")
    val senderId: Long,
    @SerializedName("senderName")
    val senderName: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("messageType")
    val messageType: MessageType,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("readAt")
    val readAt: String?,
    @SerializedName("isRead")
    val isRead: Boolean
)


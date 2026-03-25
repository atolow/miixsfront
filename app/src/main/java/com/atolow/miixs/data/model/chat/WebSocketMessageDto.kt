package com.atolow.miixs.data.model.chat

import com.atolow.miixs.data.model.MessageType
import com.google.gson.annotations.SerializedName

data class WebSocketMessageDto(
    @SerializedName("chatRoomId")
    val chatRoomId: Long?,
    @SerializedName("senderId")
    val senderId: Long?,
    @SerializedName("senderName")
    val senderName: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("messageType")
    val messageType: MessageType?,
    @SerializedName("messageId")
    val messageId: Long?,
    @SerializedName("timestamp")
    val timestamp: String?
)


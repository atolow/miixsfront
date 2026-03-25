package com.atolow.miixs.data.model.chat

import com.atolow.miixs.data.model.MessageType
import com.google.gson.annotations.SerializedName

data class SendMessageRequestDto(
    @SerializedName("chatRoomId")
    val chatRoomId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("messageType")
    val messageType: MessageType = MessageType.TEXT
)


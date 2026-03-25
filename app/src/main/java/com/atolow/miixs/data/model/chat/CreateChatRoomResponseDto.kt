package com.atolow.miixs.data.model.chat

import com.google.gson.annotations.SerializedName

data class CreateChatRoomResponseDto(
    @SerializedName("chatRoomId")
    val chatRoomId: Long,
    @SerializedName("otherUserId")
    val otherUserId: Long,
    @SerializedName("otherUserName")
    val otherUserName: String
)


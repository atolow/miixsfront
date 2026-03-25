package com.atolow.miixs.data.model.chat

import com.google.gson.annotations.SerializedName

data class CreateChatRoomRequestDto(
    @SerializedName("otherUserId")
    val otherUserId: Long
)


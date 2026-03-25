package com.atolow.miixs.data.model.user

import com.google.gson.annotations.SerializedName

data class UpdateChatSettingsRequestDto(
    @SerializedName("blockNewChats")
    val blockNewChats: Boolean
)


package com.atolow.miixs.data.model.block

import com.google.gson.annotations.SerializedName

data class BlockResponseDto(
    @SerializedName("blockedUserId")
    val blockedUserId: Long,
    @SerializedName("blockedUsername")
    val blockedUsername: String,
    @SerializedName("blockedAt")
    val blockedAt: String
)


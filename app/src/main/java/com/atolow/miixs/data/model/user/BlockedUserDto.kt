package com.atolow.miixs.data.model.user

import com.google.gson.annotations.SerializedName

data class BlockedUserDto(
    @SerializedName("blockedUserId")
    val blockedUserId: Long,
    @SerializedName("name")
    val blockedUsername: String,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?
)


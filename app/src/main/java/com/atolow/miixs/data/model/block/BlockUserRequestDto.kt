package com.atolow.miixs.data.model.block

import com.google.gson.annotations.SerializedName

data class BlockUserRequestDto(
    @SerializedName("blockedUserId")
    val blockedUserId: Long
)


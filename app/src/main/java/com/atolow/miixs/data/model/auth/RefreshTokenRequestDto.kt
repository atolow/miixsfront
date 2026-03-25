package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequestDto(
    @SerializedName("refreshToken")
    val refreshToken: String
)


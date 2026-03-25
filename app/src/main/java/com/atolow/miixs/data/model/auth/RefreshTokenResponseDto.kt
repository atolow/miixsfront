package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponseDto(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)


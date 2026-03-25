package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("token")
    val token: String
)


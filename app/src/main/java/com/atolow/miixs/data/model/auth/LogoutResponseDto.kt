package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class LogoutResponseDto(
    @SerializedName("message")
    val message: String
)


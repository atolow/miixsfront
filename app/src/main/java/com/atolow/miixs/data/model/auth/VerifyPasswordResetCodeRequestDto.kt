package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class VerifyPasswordResetCodeRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String
)


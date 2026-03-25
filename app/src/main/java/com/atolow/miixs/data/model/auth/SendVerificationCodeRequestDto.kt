package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class SendVerificationCodeRequestDto(
    @SerializedName("email")
    val email: String
)


package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("newPassword")
    val newPassword: String
)


package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequestDto(
    @SerializedName("currentPassword")
    val currentPassword: String,
    @SerializedName("newPassword")
    val newPassword: String
)


package com.atolow.miixs.data.model.notification

import com.google.gson.annotations.SerializedName

data class BanNotificationDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("expiresAt")
    val expiresAt: String,
    @SerializedName("message")
    val message: String
)


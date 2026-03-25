package com.atolow.miixs.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("path")
    val path: String,
    @SerializedName("timestamp")
    val timestamp: String?
)


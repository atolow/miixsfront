package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class FindPasswordResponseDto(
    @SerializedName("message")
    val message: String
)


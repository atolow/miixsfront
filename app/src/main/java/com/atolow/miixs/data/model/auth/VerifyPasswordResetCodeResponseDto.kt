package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class VerifyPasswordResetCodeResponseDto(
    @SerializedName("verified")
    val verified: Boolean
)


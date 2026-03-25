package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class EmailVerificationResponseDto(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("verified")
    val verified: Boolean
) {
    companion object {
        fun success(): EmailVerificationResponseDto {
            return EmailVerificationResponseDto("이메일 인증이 완료되었습니다.", true)
        }
    }
}


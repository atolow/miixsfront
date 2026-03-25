package com.atolow.miixs.data.model.auth

import com.google.gson.annotations.SerializedName

data class ResendCooldownResponseDto(
    @SerializedName("canResend")
    val canResend: Boolean,
    @SerializedName("remainingSeconds")
    val remainingSeconds: Long? = null
) {
    companion object {
        fun available(): ResendCooldownResponseDto {
            return ResendCooldownResponseDto(true, null)
        }
        
        fun cooldown(remainingSeconds: Long): ResendCooldownResponseDto {
            return ResendCooldownResponseDto(false, remainingSeconds)
        }
    }
}


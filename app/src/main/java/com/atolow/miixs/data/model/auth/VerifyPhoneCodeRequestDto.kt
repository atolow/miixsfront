package com.atolow.miixs.data.model.auth

data class VerifyPhoneCodeRequestDto(
    val phoneNumber: String,
    val code: String
)


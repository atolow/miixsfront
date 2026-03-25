package com.atolow.miixs.data.model.payment

import com.google.gson.annotations.SerializedName

data class CreatePaymentRequestDto(
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("cashAmount")
    val cashAmount: Long,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("productId")
    val productId: String? = null
)


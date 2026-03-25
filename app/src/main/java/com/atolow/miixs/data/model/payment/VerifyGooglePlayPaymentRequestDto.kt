package com.atolow.miixs.data.model.payment

import com.google.gson.annotations.SerializedName

data class VerifyGooglePlayPaymentRequestDto(
    @SerializedName("purchaseToken")
    val purchaseToken: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("orderId")
    val orderId: String
)


package com.atolow.miixs.data.model.payment

import com.google.gson.annotations.SerializedName

data class PaymentResponseDto(
    @SerializedName("paymentId")
    val paymentId: Long,
    @SerializedName("userId")
    val userId: Long? = null,
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("cashAmount")
    val cashAmount: Long,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("receiptId")
    val receiptId: String? = null,
    @SerializedName("transactionId")
    val transactionId: String? = null,
    @SerializedName("productId")
    val productId: String? = null,
    @SerializedName("completedAt")
    val completedAt: String? = null,
    @SerializedName("failureReason")
    val failureReason: String? = null,
    @SerializedName("verified")
    val verified: Boolean = false,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("modifiedAt")
    val modifiedAt: String? = null
)


package com.atolow.miixs.data.model.payment

import com.google.gson.annotations.SerializedName

data class ProductResponseDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("cashAmount")
    val cashAmount: Long,
    @SerializedName("isActive")
    val isActive: Boolean
)


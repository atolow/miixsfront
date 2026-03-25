package com.atolow.miixs.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
)

data class PageResponse<T>(
    @SerializedName("content")
    val content: List<T>,
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean
)


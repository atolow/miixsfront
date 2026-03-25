package com.atolow.miixs.data.model.report

import com.google.gson.annotations.SerializedName

data class ReportResponseDto(
    @SerializedName("reportId")
    val reportId: Long,
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("reportedUserId")
    val reportedUserId: Long,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("createdAt")
    val createdAt: String
)


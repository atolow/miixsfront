package com.atolow.miixs.data.model.report

import com.google.gson.annotations.SerializedName

data class CreateReportRequestDto(
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("reason")
    val reason: String? = null
)


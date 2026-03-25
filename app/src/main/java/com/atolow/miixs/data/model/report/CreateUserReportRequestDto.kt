package com.atolow.miixs.data.model.report

import com.google.gson.annotations.SerializedName

data class CreateUserReportRequestDto(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("reason")
    val reason: String
)


package com.atolow.miixs.data.model.post

import com.google.gson.annotations.SerializedName

data class CreatePostRequestDto(
    @SerializedName("content")
    val content: String
)


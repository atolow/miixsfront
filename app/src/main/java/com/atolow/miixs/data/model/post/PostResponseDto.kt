package com.atolow.miixs.data.model.post

import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.google.gson.annotations.SerializedName

data class PostResponseDto(
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("authorId")
    val authorId: Long,
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("authorUsername")
    val authorUsername: String?,
    @SerializedName("authorProfileImageUrl")
    val authorProfileImageUrl: String?,
    @SerializedName("authorLocation")
    val authorLocation: Location?,
    @SerializedName("authorGender")
    val authorGender: Gender?,
    @SerializedName("authorAge")
    val authorAge: Int?,
    @SerializedName("content")
    val content: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("modifiedAt")
    val modifiedAt: String?
)


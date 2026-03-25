package com.atolow.miixs.data.model.user

import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ProfileResponseDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: Gender,
    @SerializedName("age")
    val age: Int,
    @SerializedName("miixsCash")
    val miixsCash: Long,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?,
    @SerializedName("profileImageUrls")
    val profileImageUrls: List<String>?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("modifiedAt")
    val modifiedAt: String?
)


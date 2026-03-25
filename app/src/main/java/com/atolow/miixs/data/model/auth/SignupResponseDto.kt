package com.atolow.miixs.data.model.auth

import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.google.gson.annotations.SerializedName

data class SignupResponseDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("gender")
    val gender: Gender? = null,
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("miixsCash")
    val miixsCash: Long? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String? = null
)


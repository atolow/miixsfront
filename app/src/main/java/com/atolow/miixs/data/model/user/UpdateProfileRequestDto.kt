package com.atolow.miixs.data.model.user

import com.atolow.miixs.data.model.Location
import com.google.gson.annotations.SerializedName

data class UpdateProfileRequestDto(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("location")
    val location: Location? = null
)


package com.atolow.miixs.data.model.user

import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.data.model.PageResponse
import com.google.gson.annotations.SerializedName

data class DashboardResponseDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("gender")
    val gender: Gender?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("miixsCash")
    val miixsCash: Long?,
    @SerializedName("blockNewChats")
    val blockNewChats: Boolean?,
    @SerializedName("blockedUsers")
    val blockedUsers: PageResponse<BlockedUserDto>?
)


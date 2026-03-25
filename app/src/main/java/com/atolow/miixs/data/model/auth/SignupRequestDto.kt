package com.atolow.miixs.data.model.auth

import android.os.Parcelable
import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupRequestDto(
    val username: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val gender: Gender,
    val age: Int,
    val location: Location,
    val profileImagePath: String? = null
) : Parcelable


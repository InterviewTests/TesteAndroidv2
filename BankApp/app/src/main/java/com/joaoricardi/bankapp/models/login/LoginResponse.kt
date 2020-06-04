package com.joaoricardi.bankapp.models.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val userAccount: UserAccont

): Parcelable



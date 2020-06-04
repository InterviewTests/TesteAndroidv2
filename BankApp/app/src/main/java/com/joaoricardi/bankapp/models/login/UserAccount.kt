package com.joaoricardi.bankapp.models.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccont(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
): Parcelable
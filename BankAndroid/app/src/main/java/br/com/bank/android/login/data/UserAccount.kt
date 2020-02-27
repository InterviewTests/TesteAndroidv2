package br.com.bank.android.login.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable
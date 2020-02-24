package br.com.flaviokreis.santanderv2.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount(
    val userId: Long,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable
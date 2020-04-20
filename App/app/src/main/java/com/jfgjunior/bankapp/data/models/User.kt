package com.jfgjunior.bankapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName(value = "userId")
    val id: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Float
) : Parcelable {
    val bankAccountAndAgency: String
        get() = String.format("%s / %s", bankAccount, agency)
}
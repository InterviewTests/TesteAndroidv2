package com.valber.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.valber.data.extensions.empty
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.NumberFormat


@SuppressLint("ParcelCreator")
@Parcelize
data class UserAccount(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable

fun UserAccount.empty() = UserAccount(0, String.empty(), String.empty(), String.empty(), 0.0)

fun UserAccount.showAccount() = "$agency/$bankAccount"

fun UserAccount.showBalance() = NumberFormat.getCurrencyInstance().format(balance.toBigDecimal().setScale(1, RoundingMode.CEILING))


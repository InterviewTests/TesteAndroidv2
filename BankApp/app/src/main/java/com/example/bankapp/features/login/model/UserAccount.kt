package com.example.bankapp.features.login.model

import android.os.Parcelable
import com.example.bankapp.features.details.model.UserDetailsUiModel
import com.example.base.extensions.toMoneyFormat
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@Parcelize
data class UserAccount(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable {
    fun toUiModel(): UserDetailsUiModel =
        UserDetailsUiModel(
            userName = name,
            bankAccount = accoutInfo(),
            balance = balance()
        )

    private fun accoutInfo(): String{
        return "$bankAccount / $agency"
    }

    private fun balance(): String{
        val value = balance.toMoneyFormat()
        return "R$ $value"
    }
}


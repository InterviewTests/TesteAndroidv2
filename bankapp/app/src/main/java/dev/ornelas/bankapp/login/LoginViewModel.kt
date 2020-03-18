package dev.ornelas.bankapp.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginViewModel(
    val id: Int, val name: String, val bankAccount: String, val agency: String, val balance: Double
) : Parcelable
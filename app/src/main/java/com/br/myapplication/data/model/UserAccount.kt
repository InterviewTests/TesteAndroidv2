package com.br.myapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount(val userId: String,
                       val name: String,
                       val bankAccount: String,
                       val agency: String,
                       val balance: Double) : Parcelable
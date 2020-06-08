package br.com.testeandroidv2.model.bean

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginBean(val userId: Int,
                     val name: String,
                     val bankAccount: String,
                     val agency: String,
                     val balance: Double) : Parcelable
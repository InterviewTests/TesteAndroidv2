package br.com.bank.android.home.presentation.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stataments(
    val title: String,
    val desc: String,
    val date: String,
    val value: Double
) : Parcelable
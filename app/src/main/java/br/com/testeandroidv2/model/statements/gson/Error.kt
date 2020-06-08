package br.com.testeandroidv2.model.statements.gson

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Error(val code: Int? = null, var message: String? = null) : Parcelable
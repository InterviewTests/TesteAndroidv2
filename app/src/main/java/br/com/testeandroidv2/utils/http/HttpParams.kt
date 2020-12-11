package br.com.testeandroidv2.utils.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HttpParams(var nome: String? = null, var valor: String? = null) : Parcelable
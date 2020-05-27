package com.br.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Error(val code: String?, val message: String) : Parcelable
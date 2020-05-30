package com.br.myapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseStatement(val statementList: MutableList<Statement>) : Parcelable
package com.br.myapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogin(val userAccount: UserAccount, val error: Error) : Parcelable
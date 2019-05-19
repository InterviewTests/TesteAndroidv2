package br.com.teste.santander.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount( val agency: String?,
                        val balance: Double?,
                        val bankAccount: String?,
                        val name: String?,
                        val userId: Int?): Parcelable
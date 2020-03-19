package dev.ornelas.bankapp.ui.statements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatementViewModel(
    val title: String? = null,
    val desc: String? = null,
    val date: String? = null,
    val value: String? = null
) : Parcelable

@Parcelize
data class StatementsViewModel(
    val statements: List<StatementViewModel>? = arrayListOf(),
    val errorMessage: String? = null,
    val logedOut: Boolean = false
) : Parcelable
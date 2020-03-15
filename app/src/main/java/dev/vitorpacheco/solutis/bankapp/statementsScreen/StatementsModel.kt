package dev.vitorpacheco.solutis.bankapp.statementsScreen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.util.*

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
    val errorMessage: String? = null
) : Parcelable

data class StatementsRequest(val userId: Int? = null)

@Parcelize
data class StatementError(val message: String? = null) : Parcelable

@Parcelize
data class Statement(
    val title: String? = null,
    val desc: String? = null,
    val date: Date? = null,
    val value: BigDecimal? = null
) : Parcelable

@Parcelize
data class StatementsResponse(
    val statementList: List<Statement>? = arrayListOf(),
    val error: StatementError? = null
) : Parcelable
package com.tata.bank.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LoginCredentials(
    val user: String,
    val password: String
)

data class LoginResponse(
    val error: Error,
    val userAccount: UserAccount
)

data class Error(
    val code: Int,
    val message: String?
)

@Parcelize
data class UserAccount(
    val agency: String,
    val balance: Double,
    val bankAccount: String,
    val name: String,
    val userId: Int
) : Parcelable

data class Statement(
    val date: String,
    val desc: String,
    val title: String,
    val value: Int
)

data class StatementResponse(
    val error: Error,
    val statementList: List<Statement>
)
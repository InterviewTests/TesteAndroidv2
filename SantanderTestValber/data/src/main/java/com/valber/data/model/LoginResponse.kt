package com.valber.data.model


data class LoginResponse(val userAccount: UserAccount, val error: Error)

fun UserAccount.mapUserAccout(): UserAccount = UserAccount(userId, name, bankAccount, agency, balance)
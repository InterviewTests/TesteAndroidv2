package com.tata.bank.main

import com.tata.bank.login.Error

data class Statement(
    val date: String,
    val desc: String,
    val title: String,
    val value: Double
)

data class StatementResponse(
    val error: Error, // TODO replace the error model
    val statementList: List<Statement>
)

data class AccountData(
    val name: String,
    val account: String,
    val balance:String
)
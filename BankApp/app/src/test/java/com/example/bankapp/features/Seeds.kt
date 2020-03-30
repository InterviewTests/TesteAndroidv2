package com.example.bankapp.features

import com.example.bankapp.features.details.data.service.StatementResponse
import com.example.bankapp.features.details.data.service.Statements
import com.example.bankapp.features.login.model.Errors
import com.example.bankapp.features.login.model.LoginRequest
import com.example.bankapp.features.login.model.LoginResponse
import com.example.bankapp.features.login.model.UserAccount

object Seeds {
    const val NAME = "Felipe"
    const val PASSWORD = "Password"
    const val MESSAGE_ERROR = "Error"
    const val ID = 1
    const val USER_ID = "1"
    const val BANK_ACCOUNT = "123ds4"
    const val AGENCY = "2030"
    const val BALANCE = 3.14
    const val TITLE =  "debit"
    const val DATE = "2020-08-08"
    const val DESC = "desc"

    val loginMock = LoginRequest(
        NAME,
        PASSWORD
    )

    val loginResponseMock = LoginResponse(
        userAccount =  userResponseMock(),
        error = null
    )

    val loginResponseErrorMock = LoginResponse(
        userAccount = null,
        error = error()
    )

    fun userResponseMock():UserAccount = UserAccount(
        userId = ID,
        name = NAME,
        bankAccount = BANK_ACCOUNT,
        agency = AGENCY,
        balance = BALANCE
    )

    fun error(): Errors = Errors(
        message = MESSAGE_ERROR,
        code = 53
    )

    val emptyStatements = Statements(
        listOf()
    )

    val statements = Statements(
        listOf(statementsList())
    )

    fun statementsList(): StatementResponse = StatementResponse(
        title = TITLE,
        date = DATE,
        desc = DESC,
        value = BALANCE
    )
}
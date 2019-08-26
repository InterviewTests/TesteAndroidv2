package com.valber.data

import com.valber.data.model.*
import com.valber.data.model.User

val userMock = User("test_user", "Test@1")

val userAccountMock = UserAccount(1, "Jose da Silva Teste", "2050", "012314564", 3.3445)

val loginResponseMock = LoginResponse(userAccountMock, Error())

val statement = Statement("Pagamento", "Conta de luz", "2018-08-15", -50.0)

val statementResponse = StatementResponse(listOf(statement), Error())


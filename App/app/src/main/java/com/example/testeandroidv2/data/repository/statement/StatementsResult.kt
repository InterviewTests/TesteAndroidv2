package com.example.testeandroidv2.data.repository.statement

import com.example.testeandroidv2.domain.statements.Statement

sealed class StatementsResult {
    class Success(val statements: List<Statement>) : StatementsResult()
    class ApiError(val statusCode: Int) : StatementsResult()
    object ServerError : StatementsResult()
}
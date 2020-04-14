package com.example.testeandroidv2.data.repository.statement

interface StatementsRepository {

    fun getStatements(id: Int, statementsResultCallback: (result: StatementsResult) -> Unit)
}
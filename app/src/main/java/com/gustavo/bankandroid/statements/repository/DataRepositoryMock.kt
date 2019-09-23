package com.gustavo.bankandroid.statements.repository

import com.gustavo.bankandroid.statements.data.gson.StatementList
import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import io.reactivex.Single

class DataRepositoryMock : DataRepository {
    override fun getUserStatementList(id: Int, password: String): Single<StatementResponse> {
        return Single.just(mockStatementResponse())
    }

    private fun mockStatementResponse(): StatementResponse {
        return StatementResponse(
            listOf(
                StatementList("title1", "desc1", "date", 100),
                StatementList("title2", "desc2", "date", 200),
                StatementList("title3", "desc3", "date", 3300)
            ),
            Error()
        )
    }
}
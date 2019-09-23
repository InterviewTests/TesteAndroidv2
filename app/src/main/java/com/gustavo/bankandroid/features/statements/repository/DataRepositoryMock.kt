package com.gustavo.bankandroid.features.statements.repository

import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.mock.MockData
import io.reactivex.Single

class DataRepositoryMock : DataRepository {
    override fun getUserStatementList(id: Int, password: String): Single<List<UserStatementItem>> {
        return Single.just(MockData.mockStatementList())
    }
}
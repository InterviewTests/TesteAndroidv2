package com.gustavo.bankandroid.common.mock.repository

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

//mock used for ui testing purpose
class DataRepositoryMock : RepositoriesContract.DataRepository {
    override fun getUserStatementList(id: Int): Single<List<UserStatementItem>> {
        return Single.just(MockData.mockStatementList())
    }
}
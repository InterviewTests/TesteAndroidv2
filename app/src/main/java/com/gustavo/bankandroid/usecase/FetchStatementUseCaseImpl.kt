package com.gustavo.bankandroid.usecase

import com.gustavo.bankandroid.data.gson.StatementResponse
import com.gustavo.bankandroid.data.mapper.StatementMapper
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.repository.DataRepository
import io.reactivex.Single

class FetchStatementUseCaseImpl(
    private val repository: DataRepository,
    private val mapper: StatementMapper
) : FetchStatementUseCase {

    override fun execute(id: Int, password: String): Single<List<UserStatementItem>> {
        return repository.getUserStatementList(id, password)
            .map { response: StatementResponse ->
                mapper.gsonToEntity(response)
            }
    }

}



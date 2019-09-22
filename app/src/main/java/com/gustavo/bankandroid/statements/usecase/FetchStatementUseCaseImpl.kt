package com.gustavo.bankandroid.statements.usecase

import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import com.gustavo.bankandroid.statements.data.mapper.StatementMapper
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.statements.repository.DataRepository
import io.reactivex.Single

class FetchStatementUseCaseImpl(
    private val repository: DataRepository,
    private val mapper: StatementMapper
) : StatementUseCases.FetchStatementUseCase {

    override fun execute(id: Int, password: String): Single<List<UserStatementItem>> {
        return repository.getUserStatementList(id, password)
            .map { response: StatementResponse ->
                mapper.gsonToEntity(response)
            }
    }

}



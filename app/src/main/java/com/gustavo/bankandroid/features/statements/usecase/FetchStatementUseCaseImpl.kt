package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.repository.DataRepository
import io.reactivex.Single

class FetchStatementUseCaseImpl(
    private val repository: DataRepository
) : StatementUseCases.FetchStatementUseCase {

    override fun execute(id: Int): Single<List<UserStatementItem>> {
        return repository.getUserStatementList(id)

    }

}



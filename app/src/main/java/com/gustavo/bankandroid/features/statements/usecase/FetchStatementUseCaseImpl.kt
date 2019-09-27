package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.domain.contracts.StatementUseCases
import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

class FetchStatementUseCaseImpl(
    private val repository: RepositoriesContract.DataRepository
) : StatementUseCases.FetchStatementUseCase {

    override fun execute(id: Int): Single<List<UserStatementItem>> {
        return repository.getUserStatementList(id)

    }

}



package br.com.santander.android.bank.statement.di

import br.com.santander.android.bank.statement.domain.interactor.StatementInteractor
import br.com.santander.android.bank.statement.repository.StatementRepository
import br.com.santander.android.bank.statement.repository.StatementService

internal object StatementInjection {

    val interactor by lazy { StatementInteractor(statementRepository) }

    private val statementService by lazy { StatementService() }

    private val statementRepository by lazy { StatementRepository(statementService) }
}
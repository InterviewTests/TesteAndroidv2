package com.nandoligeiro.safrando.di


import com.nandoligeiro.safrando.domain.login.repository.LoginMockRepository
import com.nandoligeiro.safrando.domain.login.repository.LoginRepository
import com.nandoligeiro.safrando.domain.statements.repository.BankStatementMockRepository
import com.nandoligeiro.safrando.domain.statements.repository.BankStatementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindBankStatementRepository(
        repository: BankStatementMockRepository //BankStatementRepositoryImpl
    ): BankStatementRepository

    @Binds
    fun bindLoginRepository(
        repository: LoginMockRepository //LoginRepositoryImpl
    ): LoginRepository
}

package com.nandoligeiro.safrando.di


import com.nandoligeiro.safrando.data.bankstatement.repository.BankStatementRepositoryImpl
import com.nandoligeiro.safrando.data.login.repository.LocalLoginRepositoryImpl
import com.nandoligeiro.safrando.data.login.repository.LoginRepositoryImpl
import com.nandoligeiro.safrando.domain.login.repository.LocalLoginRepository
import com.nandoligeiro.safrando.domain.login.repository.LoginRepository
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
        repository: BankStatementRepositoryImpl
    ): BankStatementRepository

    @Binds
    fun bindLoginRepository(
        repository: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun bindLocalLoginRepository(
        repository: LocalLoginRepositoryImpl
    ): LocalLoginRepository

}

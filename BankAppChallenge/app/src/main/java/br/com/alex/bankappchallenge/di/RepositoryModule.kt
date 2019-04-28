package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.repository.LoginRepository
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.repository.StatementRepository
import br.com.alex.bankappchallenge.repository.StatementRepositoryContract
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepositoryContract> { LoginRepository(get()) }
    single<StatementRepositoryContract> { StatementRepository(get()) }
}
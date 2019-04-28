package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.interactor.mapper.StatementMapper
import br.com.alex.bankappchallenge.interactor.mapper.StatementMapperContract
import br.com.alex.bankappchallenge.interactor.mapper.UserMapper
import br.com.alex.bankappchallenge.interactor.mapper.UserMapperContract
import org.koin.dsl.module

val mapperModule = module {
    factory<StatementMapperContract> { StatementMapper() }
    factory<UserMapperContract> { UserMapper(get()) }
}
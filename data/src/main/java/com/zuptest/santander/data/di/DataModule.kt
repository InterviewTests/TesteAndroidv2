package com.zuptest.data.di

import com.zuptest.data.repository.LoginRepositoryImpl
import com.zuptest.data.repository.StatementRepositoryImpl
import com.zuptest.santander.domain.repository.LoginRepository
import com.zuptest.santander.domain.repository.StatementRepository
import org.koin.dsl.module

object DataModule {

    val module = module {

        single<LoginRepository> {
            LoginRepositoryImpl(
                api = get()
            )
        }

        single<StatementRepository> {
            StatementRepositoryImpl(
                api = get()
            )
        }
    }
}
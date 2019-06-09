package com.zuptest.santander.data.di

import com.zuptest.santander.data.local.Preferences
import com.zuptest.santander.data.local.PreferencesImpl
import com.zuptest.santander.data.repository.LoginRepositoryImpl
import com.zuptest.santander.data.repository.StatementRepositoryImpl
import com.zuptest.santander.domain.repository.LoginRepository
import com.zuptest.santander.domain.repository.StatementRepository
import org.koin.dsl.module

object DataModule {

    val module = module {

        single<LoginRepository> {
            LoginRepositoryImpl(
                api = get(),
                preferences = get()
            )
        }

        single<StatementRepository> {
            StatementRepositoryImpl(
                api = get()
            )
        }

        single<Preferences> {
            PreferencesImpl(
                sharedPreferences = get()
            )
        }
    }
}
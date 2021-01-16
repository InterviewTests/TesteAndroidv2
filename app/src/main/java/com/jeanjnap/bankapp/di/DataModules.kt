package com.jeanjnap.bankapp.di

import com.jeanjnap.data.client.ApiClient.makeService
import com.jeanjnap.data.mapper.UserDataResponseToUserAccountMapper
import com.jeanjnap.data.repository.BankRepositoryImpl
import com.jeanjnap.data.source.local.Cache
import com.jeanjnap.data.source.local.CacheImpl
import com.jeanjnap.data.source.remote.BankRemoteDataSource
import com.jeanjnap.data.source.remote.BankRemoteDataSourceImpl
import com.jeanjnap.data.source.remote.service.BankService
import com.jeanjnap.data.util.moshi.InternalMoshi
import com.jeanjnap.data.util.moshi.InternalMoshiImpl
import com.jeanjnap.domain.repository.BankRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModules {

    val serviceModulesItems = module {
        single { makeService<BankService>(get()) }
    }

    val dataModulesItems = module {
        single<InternalMoshi> { InternalMoshiImpl() }
        single<Cache> { CacheImpl(get(), get()) }

        // DATA SOURCES
        single<BankRemoteDataSource> {
            BankRemoteDataSourceImpl(
                get(),
                get(named(UserDataResponseToUserAccountMapper::javaClass.name))
            )
        }

        // REPOSITORIES
        single<BankRepository> { BankRepositoryImpl(get()) }
    }
}

package com.develop.tcs_bank.di

import android.content.Context
import com.develop.tcs_bank.domain.interfaces.UserRepository
import com.develop.tcs_bank.infrastructure.repositories.disk.SPUtils
import com.develop.tcs_bank.infrastructure.repositories.impl.UserRepositoryImpl
import com.develop.tcs_bank.infrastructure.repositories.remote.IApiServiceUser
import dagger.Module
import dagger.Provides

@Module
class ModuleRepository {

    @Provides
    internal fun provideUserRepository(service: IApiServiceUser, context: Context, spUtils: SPUtils): UserRepository {
        return UserRepositoryImpl(service, context, spUtils)
    }

}
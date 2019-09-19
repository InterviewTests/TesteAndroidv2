package com.develop.tcs_bank.di

import com.develop.tcs_bank.infrastructure.repositories.remote.IApiServiceUser
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class ModuleApi {

    @Provides
    fun providesUserAPI(retrofit: Retrofit): IApiServiceUser {
        return retrofit.create(IApiServiceUser::class.java)
    }

}
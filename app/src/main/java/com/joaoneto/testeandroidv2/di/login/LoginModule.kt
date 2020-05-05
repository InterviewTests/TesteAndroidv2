package com.joaoneto.testeandroidv2.di.login

import com.joaoneto.testeandroidv2.loginscreen.service.LoginService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {
    @Provides
    fun provideLogin(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}
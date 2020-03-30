package com.example.bankapp.features.login.di

import androidx.lifecycle.ViewModel
import com.example.bankapp.features.login.data.repository.LoginRepository
import com.example.bankapp.features.login.data.service.LoginService
import com.example.bankapp.features.login.presentation.LoginActivityViewModel
import com.example.base.annotation.ActivityScope
import com.example.base.annotation.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Named

@Module
class LoginActivityModule {

    @Provides
    @ActivityScope
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    fun provideViewModel(viewModel: LoginActivityViewModel): ViewModel = viewModel

    @Provides
    @ActivityScope
    fun provideRepository(repository: LoginRepository.Impl): LoginRepository = repository

    @Provides
    @ActivityScope
    fun provideService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Named("IO")
    @Provides
    fun provideIO(): Scheduler = Schedulers.io()

    @Named("Main")
    @Provides
    fun provideMain():Scheduler= AndroidSchedulers.mainThread()
}
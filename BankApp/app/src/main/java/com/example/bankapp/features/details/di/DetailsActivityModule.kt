package com.example.bankapp.features.details.di

import androidx.lifecycle.ViewModel
import com.example.bankapp.features.details.data.repository.DetailsRepository
import com.example.bankapp.features.details.data.service.StatementService
import com.example.bankapp.features.details.presentantion.DetailsActivity
import com.example.bankapp.features.details.presentantion.DetailsActivityViewModel
import com.example.bankapp.features.login.model.UserAccount
import com.example.base.annotation.ActivityScope
import com.example.base.annotation.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module
class DetailsActivityModule {

    @ActivityScope
    @Provides
    fun provideBundle(activity: DetailsActivity): UserAccount = activity.userAccount

    @Provides
    @ActivityScope
    fun provideService(retrofit: Retrofit): StatementService = retrofit.create()

    @Provides
    @ActivityScope
    fun provideRepository(repository: DetailsRepository.Impl): DetailsRepository = repository

    @Provides
    @ActivityScope
    @IntoMap
    @ViewModelKey(DetailsActivityViewModel::class)
    fun provideViewModel(viewWModel: DetailsActivityViewModel): ViewModel = viewWModel

    @Named("IO")
    @Provides
    fun provideIO(): Scheduler = Schedulers.io()

    @Named("Main")
    @Provides
    fun provideMain(): Scheduler = AndroidSchedulers.mainThread()
}
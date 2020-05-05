package com.joaoneto.testeandroidv2.di

import androidx.lifecycle.ViewModelProvider
import com.joaoneto.testeandroidv2.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
    
}

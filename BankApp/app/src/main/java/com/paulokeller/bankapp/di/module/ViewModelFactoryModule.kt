package com.paulokeller.bankapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulokeller.bankapp.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }
}
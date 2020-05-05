package com.joaoneto.testeandroidv2.di.main

import androidx.lifecycle.ViewModel
import com.joaoneto.testeandroidv2.di.ViewModelKey
import com.joaoneto.testeandroidv2.mainscreen.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
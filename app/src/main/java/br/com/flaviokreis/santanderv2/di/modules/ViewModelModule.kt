package br.com.flaviokreis.santanderv2.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.flaviokreis.santanderv2.viewmodels.BankViewModelFactory
import br.com.flaviokreis.santanderv2.viewmodels.LoginViewModel
import br.com.flaviokreis.santanderv2.viewmodels.StatementViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: BankViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(model: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatementViewModel::class)
    abstract fun bindStatementViewModel(model: StatementViewModel): ViewModel
}
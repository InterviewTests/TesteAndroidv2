package pt.felipegouveia.bankapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pt.felipegouveia.bankapp.di.annotation.ViewModelKey
import pt.felipegouveia.bankapp.di.helper.ViewModelFactory
import pt.felipegouveia.bankapp.presentation.login.LoginViewModel
import pt.felipegouveia.bankapp.presentation.statements.StatementsViewModel


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatementsViewModel::class)
    abstract fun bindStatementsViewModel(statementsViewModel: StatementsViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

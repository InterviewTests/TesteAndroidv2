package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.felipegouveia.bankapp.presentation.login.LoginFragment
import pt.felipegouveia.bankapp.presentation.statements.StatementsFragment

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun bindStatementsFragment() : StatementsFragment

}
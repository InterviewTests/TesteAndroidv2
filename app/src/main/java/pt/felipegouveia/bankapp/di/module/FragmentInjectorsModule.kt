package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pt.felipegouveia.bankapp.presentation.login.LoginFragment

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment



}
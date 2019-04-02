package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): LoginFragment

}

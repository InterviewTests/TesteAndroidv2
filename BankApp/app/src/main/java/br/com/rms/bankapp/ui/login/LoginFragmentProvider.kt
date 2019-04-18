package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    internal abstract fun contributesMovieListFragment(): LoginFragment

}

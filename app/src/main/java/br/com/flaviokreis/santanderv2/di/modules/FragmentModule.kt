package br.com.flaviokreis.santanderv2.di.modules

import br.com.flaviokreis.santanderv2.ui.fragments.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}
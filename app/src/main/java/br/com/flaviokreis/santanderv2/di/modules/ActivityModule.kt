package br.com.flaviokreis.santanderv2.di.modules

import br.com.flaviokreis.santanderv2.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
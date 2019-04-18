package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun contributesMovieListFragment(): HomeFragment


}

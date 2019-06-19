package com.farage.testeandroidv2.di.component

import com.farage.testeandroidv2.di.ClassModule
import com.farage.testeandroidv2.ui.HomeActivity
import dagger.Component

@Component(modules = [ClassModule::class])
interface ComponentExemple {
    fun inject(activity: HomeActivity)
}
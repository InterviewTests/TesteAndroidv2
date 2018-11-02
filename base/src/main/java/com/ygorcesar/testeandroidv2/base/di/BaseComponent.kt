package com.ygorcesar.testeandroidv2.base.di

import android.arch.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import com.ygorcesar.testeandroidv2.base.data.preferences.PreferencesHelper

/**
 * Function to get the current [BaseComponent] instance
 */
fun base() = BaseComponent.INSTANCE

/**
 * Base component to all Dagger components
 */
interface BaseComponent {
    /**
     * The [PreferencesHelper] injected by Dagger
     */
    val preferencesHelper: PreferencesHelper

    /**
     * The [ViewModelProvider.Factory] injected by Dagger
     */
    val viewModelFactory: ViewModelProvider.Factory

    val moshi: Moshi

    companion object {
        lateinit var INSTANCE: BaseComponent
    }
}
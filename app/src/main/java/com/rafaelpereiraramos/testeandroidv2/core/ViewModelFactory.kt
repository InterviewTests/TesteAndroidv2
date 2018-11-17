package com.rafaelpereiraramos.testeandroidv2.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]

        if (creator == null) {
            for ((viewModelClass, providerOfViewModel) in creators)
                if (modelClass.isAssignableFrom(viewModelClass)) {
                    creator = providerOfViewModel
                    break
                }
        }

        val viewModel = creator?.get() ?: throw IllegalArgumentException(
            String.format("%s isn't binded in ViewModelModule", modelClass.canonicalName))

        @Suppress("UNCHECKED_CAST")
        return viewModel as T
    }
}
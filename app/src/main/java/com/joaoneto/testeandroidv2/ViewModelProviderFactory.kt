package com.joaoneto.testeandroidv2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel?>?, Provider<ViewModel>?>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) { // if the viewmodel has not been created
// loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ((key, value) in creators) { // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(key!!)) {
                    creator = value
                    break
                }
            }
        }
        // if this is not one of the allowed keys, throw exception
        requireNotNull(creator) { "unknown model class $modelClass" }
        // return the Provider
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val TAG = "ViewModelProviderFactor"
    }

}

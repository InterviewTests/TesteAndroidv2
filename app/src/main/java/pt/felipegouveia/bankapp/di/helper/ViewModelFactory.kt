package pt.felipegouveia.bankapp.di.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        val creator = creators[viewModelClass] ?: creators.entries.firstOrNull {
            viewModelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown viewmodel class $viewModelClass")
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
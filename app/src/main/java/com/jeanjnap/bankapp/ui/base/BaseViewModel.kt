package com.jeanjnap.bankapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanjnap.domain.boundary.ResourcesString
import com.jeanjnap.infrastructure.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.concurrent.CancellationException

abstract class BaseViewModel(
    private val network: Network
) : DefaultViewModel() {

    protected val resourcesString: ResourcesString by inject()

    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun launchDataLoad(
        checkConnection: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return uiScope.launch {
            if (checkConnection && network.hasActiveInternetConnection()) {
                try {
                    _loading.value = true
                    block()
                } catch (exception: Exception) {
                    doOnError(exception)
                } finally {
                    _loading.value = false
                }
            } else {
                _error.value = resourcesString.noConnectionError
            }
        }
    }

    fun displayError(message: String? = null) {
        _error.value = message ?: resourcesString.genericError
    }

    private fun doOnError(exception: Exception) {
        if (exception !is CancellationException) {
            displayError()
        }
    }
}

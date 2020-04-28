package br.com.raphael.everis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.raphael.everis.App
import br.com.raphael.everis.model.Statement
import br.com.raphael.everis.repository.BackendRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatementsViewModel(application: Application) : AndroidViewModel(application) {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _success = MutableLiveData<List<Statement>>()
    val success: MutableLiveData<List<Statement>>
        get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    @Inject
    lateinit var backendRepository: BackendRepository

    init {
        getApplication<App>().component.inject(this)
    }

    fun getStatements(userId: Int) {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val response = backendRepository.getStatementAsync(userId = userId)
                    _success.postValue(response.statementList)
                    _loading.postValue(false)
                } catch (e: Exception) {
                    _error.postValue(e.toString())
                }
        }
    }
}

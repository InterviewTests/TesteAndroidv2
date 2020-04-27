package br.com.raphael.everis.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.*
import br.com.raphael.everis.di.DaggerAppComponent
import br.com.raphael.everis.di.module.AppModule
import br.com.raphael.everis.di.module.RemoteModule
import br.com.raphael.everis.model.FieldError
import br.com.raphael.everis.model.UserAccount
import br.com.raphael.everis.repository.BackendRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import br.com.raphael.everis.R


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _success = MutableLiveData<UserAccount>()
    val success: MutableLiveData<UserAccount>
        get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _user = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _user

    fun setUser(value: String){
        _user.postValue(value)
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun setPassword(value: String){
        _password.postValue(value)
    }

    @Inject
    lateinit var backendRepository: BackendRepository
    @Inject
    lateinit var preferences: SharedPreferences

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .remoteModule(RemoteModule())
            .build().inject(this)
    }

    fun postLogin(): List<FieldError> {
        val checkedFields = assertFields()
        if (checkedFields.isEmpty()) {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val response = backendRepository.postLoginAsync(_user.value!!, _password.value!!)
                    if(response.error.message.isNotEmpty()) {
                        _error.postValue(response.error.message)
                    } else {
                        _success.postValue(response.userAccount)

                        preferences.edit()
                            .putInt("userId", response.userAccount.userId)
                            .putString("name", response.userAccount.name)
                            .putString("agency", response.userAccount.agency)
                            .putString("bankAccount", response.userAccount.bankAccount)
                            .putFloat("balance", response.userAccount.balance.toFloat())
                            .apply()
                    }
                    _loading.postValue(false)
                } catch (e: Exception) {
                    _error.postValue(e.toString())
                }
            }
        }

        return checkedFields
    }

    private fun assertFields(): List<FieldError> {
        val fields = arrayListOf<FieldError>()

        if (_user.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_user, R.string.msg_user_obrigatorio))
        }

        if (_password.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_password, R.string.msg_password_obrigatorio))
        }

        return fields
    }
}

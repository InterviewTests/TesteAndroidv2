package br.com.raphael.everis.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.raphael.everis.App
import br.com.raphael.everis.R
import br.com.raphael.everis.extensions.*
import br.com.raphael.everis.model.FieldError
import br.com.raphael.everis.model.User
import br.com.raphael.everis.model.UserAccount
import br.com.raphael.everis.model.enums.Constants
import br.com.raphael.everis.repository.BackendRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


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

    fun setUser(value: String) {
        _user.postValue(value)
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun setPassword(value: String) {
        _password.postValue(value)
    }

    private val _data = MutableLiveData<User>()
    val data: LiveData<User>
        get() = _data

    @Inject
    lateinit var backendRepository: BackendRepository

    @Inject
    lateinit var preferences: SharedPreferences

    init {
        getApplication<App>().component.inject(this)

        if (!preferences.getString(Constants.NAME.value, "").isNullOrEmpty()) {
            _data.postValue(
                User(
                    name = (preferences.getString("name", "") ?: "").decrypt(),
                    desc = getApplication<App>().applicationContext.formatAgency((preferences.getString(Constants.AGENCY.value, "") ?: "").decrypt(), (preferences.getString(Constants.BANK_ACCOUNT.value, "") ?: "").decrypt())
                )
            )
        }
    }

    fun postLogin(): List<FieldError> {
        val checkedFields = assertFields()
        if (checkedFields.isEmpty()) {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val response =
                        backendRepository.postLoginAsync(_user.value!!, _password.value!!)
                    if (!response.error.message.isNullOrBlank()) {
                        _error.postValue(response.error.message)
                    } else {
                        _success.postValue(response.userAccount)

                        preferences.edit()
                            .putString(Constants.NAME.value, response.userAccount.name.encrypt())
                            .putString(Constants.AGENCY.value, response.userAccount.agency.encrypt())
                            .putString(Constants.BANK_ACCOUNT.value, response.userAccount.bankAccount.encrypt())
                            .apply()
                    }
                    _loading.postValue(false)
                } catch (e: Exception) {
                    _error.postValue(e.toString())
                    _loading.postValue(false)
                }
            }
        }

        return checkedFields
    }

    private fun assertFields(): List<FieldError> {
        val fields = arrayListOf<FieldError>()

        // Verificar se User esta vazio
        if (_user.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_user, R.string.msg_user_obrigatorio))
        } else
        // Verificar se conteúdo do User é somente numérico
            if (_user.value.isNumeric()) {
                // Válidar CPF
                if (!_user.value.isValidCPF()) {
                    fields.add(FieldError(R.id.til_user, R.string.msg_user_cpf_invalido))
                }
            } else {
                // Válidar E-mail
                if (!_user.value.isValidEmail()) {
                    fields.add(FieldError(R.id.til_user, R.string.msg_user_email_invalido))
                }
            }

        // Verificar se Password esta vazio
        if (_password.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_password, R.string.msg_password_obrigatorio))
        } else
        // Verificar se o password atende as validações
            if (!_password.value.isValidPassword()) {
                fields.add(FieldError(R.id.til_password, R.string.msg_password_invalido))
            }

        return fields
    }
}

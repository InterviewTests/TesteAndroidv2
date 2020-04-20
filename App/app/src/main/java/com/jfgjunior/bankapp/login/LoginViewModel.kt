package com.jfgjunior.bankapp.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfgjunior.bankapp.data.external.Repository
import com.jfgjunior.bankapp.data.models.ResponseError
import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.User
import com.jfgjunior.bankapp.data.models.UserCredentials
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

private const val TAG = "LoginViewModel"

class LoginViewModel(private val repository: Repository) : ViewModel(), KoinComponent {

    var user
        set(value) {
            credentials.user = value
            checkUserError()
        }
        get() = credentials.user
    var password
        set(value) {
            credentials.password = value
            checkPasswordError()
        }
        get() = credentials.password

    private val credentials = UserCredentials()

    private var checkErrors = false

    private var disposable: Disposable? = null

    private val _successLogin = MutableLiveData<User>()
    val successLogin: LiveData<User>
        get() = _successLogin

    private val _failLogin = MutableLiveData<ResponseError>()
    val failLogin: LiveData<ResponseError>
        get() = _failLogin

    private val _invalidUser = MutableLiveData<Boolean>()
    val invalidUser: LiveData<Boolean>
        get() = _invalidUser

    private val _invalidPassword = MutableLiveData<Boolean>()
    val invalidPassword: LiveData<Boolean>
        get() = _invalidPassword

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    init {
        val credentials = repository.getUser()
        if (credentials != null && !credentials.isEmpty()) {
            silentLogin(credentials)
        } else {
            _showLoading.value = false
        }
    }

    fun tryLogin() {
        checkErrors = true
        val isPasswordOk = checkPasswordError()
        val isUserOk = checkUserError()

        if (isPasswordOk && isUserOk) {
            login()
        }
    }

    fun onDestroy() {
        disposable?.dispose()
    }

    override fun onCleared() {
        onDestroy()
        super.onCleared()
    }

    private fun login() {
        _showLoading.value = true
        disposable?.dispose()
        disposable = repository.loginUser(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _showLoading.value = false
                handleResponse(it)
            }, {
                Log.e(TAG, "Error trying to login", it)
                _showLoading.value = false
                handleGenericError()
            })
    }

    private fun silentLogin(credentials: UserCredentials) {
        disposable?.dispose()
        disposable = repository.loginUser(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _showLoading.value = false
                _successLogin.value = response.result
            }, {
                Log.e(TAG, "Error trying to login", it)
                _showLoading.value = false
            })
    }

    private fun handleResponse(response: ResponseWrapper<User>) {
        when {
            response.result?.id != 0 -> {
                _successLogin.value = response.result
                repository.saveUser(credentials)
            }
            response.error.code != 0 -> _failLogin.value = response.error
            else -> handleGenericError()
        }
    }

    private fun handleGenericError() {
        _failLogin.value = ResponseError.genericError
    }

    private fun checkUserError(): Boolean {
        val isValid = credentials.isUserValid()
        if (checkErrors) {
            _invalidUser.value = isValid
        }
        return isValid
    }

    private fun checkPasswordError(): Boolean {
        val isValid = credentials.isPasswordValid()
        if (checkErrors) {
            _invalidPassword.value = isValid
        }
        return isValid
    }
}
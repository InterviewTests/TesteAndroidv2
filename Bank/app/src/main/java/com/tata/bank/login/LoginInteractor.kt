package com.tata.bank.login

import android.content.Context
import com.tata.bank.exceptions.InvalidCredentialsException
import com.tata.bank.network.ApiFactory
import com.tata.bank.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

interface LoginInteractorInput {
    fun doLogin(user: String, password: String)
    fun verifyLogin()
}

class LoginInteractor : LoginInteractorInput {
    lateinit var output: LoginPresenterInput
    lateinit var context: Context
    private val repository by lazy { Repository(context) }

    override fun verifyLogin() {
        repository.getCredentials()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ credentials ->
                output.fillLoginFields(credentials.user, credentials.password)
            }, {
                output.presentError(it)
            })
    }

    override fun doLogin(user: String, password: String) {
        if (isLoginCredentialsValid(user, password)) {
            fetchLogin(user, password)
        } else {
            output.presentError(InvalidCredentialsException())
        }
    }

    private fun fetchLogin(user: String, password: String) {
        val credentials = LoginCredentials(user, password)

        val response = ApiFactory.api
            .login(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it, credentials) },
                this::onError
            )
    }

    private fun isLoginCredentialsValid(user: String, password: String): Boolean {
        val isPasswordValid = CredentialsValidatorWorker.isPasswordValid(password)
        val isUserValid = CredentialsValidatorWorker.isUserValid(user)

        return isPasswordValid && isUserValid
    }

    private fun onSuccess(response: Response<LoginResponse>, credentials: LoginCredentials) {
        if (response.isSuccessful) {
            repository.saveCredentials(credentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    output.presentSuccess(response.body())
                }, this::onError)
        } else {
            output.presentStatusError(response.code())
        }
    }

    private fun onError(error: Throwable) {
        output.presentError(error)
    }
}
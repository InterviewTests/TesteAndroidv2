package com.tata.bank.login

import com.tata.bank.exceptions.InvalidCredentialsException
import com.tata.bank.network.ApiFactory
import com.tata.bank.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

interface LoginInteractorInput {
    fun fetchLogin(user: String, password: String)
}

class LoginInteractor: LoginInteractorInput {

    lateinit var output: LoginPresenterInput
    private val repository = Repository()

    override fun fetchLogin(user: String, password: String) {

        repository.saveCredentials(LoginCredentials(user, password))
        val credentials2 = repository.getCredentials()

        if (isLoginCredentialsValid(user, password)) {
        val credentials = LoginCredentials(user, password)
//            val credentials = LoginCredentials("test_user", "Test@1")


            val response = ApiFactory.api
                .login(credentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::onSuccess,
                    this::onError
                )
        } else {
            output.presentError(InvalidCredentialsException())
        }
    }

    private fun isLoginCredentialsValid(user: String, password: String): Boolean {
        val isPasswordValid = CredentialsValidatorWorker.isPasswordValid(password)
        val isUserValid = CredentialsValidatorWorker.isUserValid(user)

        return isPasswordValid && isUserValid
    }

    private fun onSuccess(response: Response<LoginResponse>) {
        if (response.isSuccessful) {
            output.presentSuccess(response.body())
        } else {
            output.presentStatusError(response.code())
        }
    }

    private fun onError(error: Throwable) {
        output.presentError(error)
    }
}
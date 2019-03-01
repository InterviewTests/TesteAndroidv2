package com.example.androidtest.repository

import android.annotation.SuppressLint
import com.example.androidtest.api.ServiceManager
import com.example.androidtest.api.ServiceResponseException
import com.example.androidtest.api.serviceCall
import java.text.ParseException

object UserRepository {


    @SuppressLint("CheckResult")
    fun loginCall(user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {

        ServiceManager.getApi().postLogin(user, pass).serviceCall(
            {
                checkinAccount(Account(it.userAccount!!))
                callback(SuccessResponse())
            },
            {
                when (it) {
                    is ServiceResponseException,
                    is NullPointerException,
                    is ParseException -> callback(FailureResponse("Erro ao processar a resposta do Servidor."))

                    else -> callback(FailureResponse("Não foi possível realizar o Login."))
                }
            }
        )
    }

    fun logoff() {
        checkoutAccount()
        // TODO: When logged out, navigate to LoginActivity
//        goToLoginActivity()
    }

    // TODO: Store app Credentials
    private fun checkinAccount(account: Account) {
        AccountRepository.loggedAccount = account
    }

    // TODO: Wipe app Credentials
    private fun checkoutAccount() {
        AccountRepository.loggedAccount = null
    }
}




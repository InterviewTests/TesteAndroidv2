package com.example.androidtest.repository

import com.example.androidtest.api.ServiceManager
import com.example.androidtest.api.ServiceResponseException
import com.example.androidtest.api.serviceCall
import java.text.ParseException
import java.util.*

object Repository {


    fun loginCall(user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {

        ServiceManager.getApi().postLogin(user, pass).serviceCall(
            {
                checkinAccount(user, pass, UserAccount(it.userAccount))
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


    fun getRecentStatements(callback: (ApiResponse, ArrayList<Statement>) -> Unit) {
        val userAccount = getLoggedAccount()!!

        ServiceManager.getApi().getStatements(userAccount.userId).serviceCall(
            {

            }, {

            }
        )
    }

    fun getLoggedAccount(): UserAccount? {

    }

    fun logoff() {
        checkoutAccount()
        // TODO: When logged out, navigate to LoginActivity
//        goToLoginActivity()
    }

    // TODO: Store app Credentials
    private fun checkinAccount(user: String, pass: String, userAccount: UserAccount) {

    }

    // TODO: Wipe app Credentials
    private fun checkoutAccount() {

    }
}




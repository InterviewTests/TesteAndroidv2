package com.example.androidtest.repository

import android.os.Handler

object UserRepository {

    var loggedUser: User? = null

    fun loginCall(user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {
//        Handler().postDelayed({
            if (user == "test_user" && pass == "Test@1"){
                checkinUser(User("Jose da Silva Teste"))
                callback(SuccessResponse())
            } else callback(FailureResponse("Login inválido. Verifique os dados digitados."))
//        }, 1800)
    }

    private fun checkinUser(user: User) {
        loggedUser = user
    }

    fun logoff() {
        loggedUser = null
    }

    fun getUserName() = loggedUser?.name ?: ""
}

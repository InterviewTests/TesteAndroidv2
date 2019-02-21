package com.example.androidtest.presentation.login

import android.os.Handler
import com.example.androidtest.repository.ApiResponse
import com.example.androidtest.repository.FailureResponse
import com.example.androidtest.repository.SuccessResponse
import com.example.androidtest.repository.User

open class LoginRepository {

    var loggedUser: User? = null

    fun loginCall(user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {
        Handler().postDelayed({
            if (user == "test_user" && pass == "Test@1") callback(SuccessResponse())
            else callback(FailureResponse("Login inválido. Verifique os dados digitados."))
        }, 1800)
    }

    fun checkinUser(user: User) {
        loggedUser = user
    }

    fun logoff() {
        loggedUser = null
    }


}

package com.example.androidtest.login

import com.example.androidtest.api.ApiResponse
import com.example.androidtest.repository.User

open class LoginRepository {

    var loggedUser: User? = null

    fun loginCall(user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {

    }

    fun checkinUser(user: User) {

    }


}

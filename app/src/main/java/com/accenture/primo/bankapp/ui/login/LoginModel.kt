package com.accenture.primo.bankapp.ui.login

import com.accenture.primo.bankapp.model.Error
import com.accenture.primo.bankapp.model.User
import com.google.gson.annotations.SerializedName

class LoginModel
{
    class LoginViewModel(val user: User, val login: LoginRequest)

    class LoginRequest(val user: String, val password: String)

    class LoginResponse(
        @SerializedName(value = "userAccount")
        val user: User,
        @SerializedName(value = "error")
        val error: Error
    )
}



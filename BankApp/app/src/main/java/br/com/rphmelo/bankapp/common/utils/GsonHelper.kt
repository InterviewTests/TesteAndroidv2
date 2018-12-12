package br.com.rphmelo.bankapp.common.utils

import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import com.google.gson.Gson

class GsonHelper {

    fun toJson(value: Any): String? = Gson().toJson(value)

    fun fromJsonLoginRequest(value: String): LoginRequest = Gson().fromJson(value, LoginRequest::class.java)
    fun fromJsonLoginResponse(value: String): LoginResponse = Gson().fromJson(value, LoginResponse::class.java)

}
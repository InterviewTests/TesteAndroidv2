package br.com.rphmelo.bankapp.login.repository

import android.content.SharedPreferences
import br.com.rphmelo.bankapp.common.utils.Variables.LOGIN_SESSION_KEY
import br.com.rphmelo.bankapp.login.api.LoginService
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import com.google.gson.Gson
import retrofit2.Call

class LoginRepository(private val loginService: LoginService, private val preferences: SharedPreferences) {
    fun login(request: LoginRequest): Call<LoginResponse> {
        return loginService.login(request)
    }

    fun setLoginSession(login: LoginRequest){
        preferences?.edit().let {
            it.putString(LOGIN_SESSION_KEY, Gson().toJson(login))
            it.apply()
        }
    }

    fun getLoginSession(): LoginRequest? {
        val session = preferences.getString(LOGIN_SESSION_KEY, "")
        return if (session.isNotEmpty()) {
            Gson().fromJson(session, LoginRequest::class.java)
        } else null
    }
}
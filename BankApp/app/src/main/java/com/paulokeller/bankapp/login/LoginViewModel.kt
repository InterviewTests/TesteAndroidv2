package com.paulokeller.bankapp.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.paulokeller.bankapp.models.Account
import com.paulokeller.bankapp.services.Client
import com.paulokeller.bankapp.services.UserDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private var loginState: MutableLiveData<LoginState<Account>> = MutableLiveData()
    private val client = Client


    fun login(user: String, password: String): LoginState<Account> {
        var state:LoginState<Account>? = null

        if (!validatePassword(password) || user.trim().isEmpty()) {
            state = LoginState("Invalid password or user", null)
            return state
        }

        try {
            val body = UserDTO(user, password)
            val call = client.instance?.login(body)
            call!!.enqueue(object : Callback<Account?> {
                override fun onFailure(call: Call<Account?>, t: Throwable?) {

                }
                override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                    if (response.body() != null) {
                        state = LoginState<Account>(null, response.body())
                    }
                }
            })
        } catch (ex: Exception) {
           return LoginState("Fail to execute request", null)
        }
        return state!!;
    }

    private fun validatePassword(value: String): Boolean {
        val regex = """^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\${'$'}%^&*])""".toRegex();
        return regex.containsMatchIn(value)
    }
}

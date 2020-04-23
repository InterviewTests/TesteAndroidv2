package com.paulokeller.bankapp.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulokeller.bankapp.models.Account
import com.paulokeller.bankapp.models.AppState
import com.paulokeller.bankapp.services.Client
import com.paulokeller.bankapp.services.UserDTO
import com.paulokeller.bankapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private val client = Client

    val loginState: MutableLiveData<AppState<Account>> = MutableLiveData()


    fun login(user: String, password: String) {
        if (!Utils.validatePassword(password) || user.trim().isEmpty()) {
            loginState.value = AppState("Invalid password or user", null)
        }

        try {
            val body = UserDTO(user, password)
            val call = client.instance?.login(body)
            call!!.enqueue(object : Callback<Account?> {
                override fun onFailure(call: Call<Account?>, t: Throwable?) {

                }

                override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                    if (response.body() != null) {

                        loginState.value = AppState<Account>(null, response.body())
                    }
                }
            })
        } catch (ex: Exception) {
            loginState.value = AppState("Fail to execute request", null)
        }
    }
}

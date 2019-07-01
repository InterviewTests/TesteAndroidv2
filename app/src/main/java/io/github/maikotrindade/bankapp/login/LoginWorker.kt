package io.github.maikotrindade.bankapp.login

import io.github.maikotrindade.bankapp.base.network.BaseNetwork
import io.github.maikotrindade.bankapp.base.util.SharedPrefsUtil
import io.github.maikotrindade.bankapp.login.model.LoginResponse
import io.github.maikotrindade.bankapp.login.model.User
import io.github.maikotrindade.bankapp.login.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.lang.ref.WeakReference

interface LoginWorkerInput {
    fun startLogin(user: User)
}

class LoginWorker(val output: WeakReference<LoginInteractor>) : LoginWorkerInput {

    override fun startLogin(user: User) {
        val loginResponseCall = BaseNetwork.get(LoginInterface::class.java).performLogIn(user)
        loginResponseCall.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    output.get()?.onLogInSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                output.get()?.onLogInError()
            }

        })
    }

    fun saveToSharedPreferences(userData: UserData) {
        SharedPrefsUtil.save("name", userData.name)
        SharedPrefsUtil.save("agency", userData.agency)
        SharedPrefsUtil.save("bankAccount", userData.bankAccount)
        SharedPrefsUtil.save("userid", userData.userId)
    }
}

interface LoginInterface {

    @POST("login")
    fun performLogIn(@Body user: User): Call<LoginResponse>

}
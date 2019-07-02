package io.github.maikotrindade.bankapp.login.domain

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

class LoginWorker(val output: WeakReference<LoginInteractor>) :
    LoginWorkerInput {

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
        SharedPrefsUtil.save(SharedPrefsUtil.userId, userData.userId)
        SharedPrefsUtil.save(SharedPrefsUtil.name, userData.name)
        SharedPrefsUtil.save(SharedPrefsUtil.agency, userData.agency)
        SharedPrefsUtil.save(SharedPrefsUtil.bankAccount, userData.bankAccount)
        SharedPrefsUtil.save(SharedPrefsUtil.balance, userData.balance)
    }
}

interface LoginInterface {

    @POST("login")
    fun performLogIn(@Body user: User): Call<LoginResponse>

}
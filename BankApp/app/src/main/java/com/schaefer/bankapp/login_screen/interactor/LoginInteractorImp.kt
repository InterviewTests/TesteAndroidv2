package com.schaefer.bankapp.login_screen.interactor

import android.content.Context
import com.schaefer.bankapp.R
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.login.LoginResult
import com.schaefer.bankapp.service.ApiProvider
import com.schaefer.bankapp.service.ApiProviderImp
import com.schaefer.bankapp.util.Constants.Companion.SHARED_HAS_LOGIN
import com.schaefer.bankapp.util.Constants.Companion.SHARED_LOGIN
import com.schaefer.bankapp.util.Constants.Companion.SHARED_PASSWORD
import com.schaefer.bankapp.util.Constants.Companion.SHARED_USERNAME
import com.schaefer.bankapp.util.error.ErrorHandler
import com.schaefer.bankapp.util.shared_preferences.SecureSharedPreferences
import com.schaefer.bankapp.util.shared_preferences.SharedUtils.Companion.deleteSharedPreference
import com.schaefer.bankapp.util.shared_preferences.SharedUtils.Companion.loadShared
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImp : LoginInteractorInput {
    override fun checkLastUser(
        context: Context,
        finishedListener: LoginInteractorInput.FinishedListener
    ) {
        if (loadShared(SHARED_LOGIN, SHARED_HAS_LOGIN, context.applicationContext) != null) {
            val username = loadShared(SHARED_LOGIN, SHARED_USERNAME, context.applicationContext)
            val password = SecureSharedPreferences(
                context,
                SHARED_LOGIN,
                context.resources.getString(R.string.key_password),
                true
            ).getString(SHARED_PASSWORD)
            if (username != null && username.isNotEmpty() && password != null && password.isNotEmpty()) {
                finishedListener.hasUserLogged(LoginModel(username, password))
            } else {
                deleteSharedPreference(SHARED_LOGIN, context)
            }
        } else {
            finishedListener.noUserLogged()
            deleteSharedPreference(SHARED_LOGIN, context)
        }
    }

    override fun makeLogin(
        request: LoginModel,
        context: Context,
        finishedListener: LoginInteractorInput.FinishedListener
    ) {
        val call = ApiProviderImp.connection.create(ApiProvider::class.java).makeLogin(request)
        call.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                if (response.raw().isSuccessful && response.body() != null) {
                    if (response.body()?.error?.message.isNullOrEmpty()) {
                        response.body()?.userAccount?.let { finishedListener.successLogin(it) }
                    } else {
                        response.body()?.error?.let { finishedListener.errorLogin(it) }
                    }
                } else {
                    finishedListener.genericError(ErrorHandler(1).getMessageFromCode())
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                finishedListener.genericError(ErrorHandler(2).getMessageFromCode())
            }
        })
    }
}
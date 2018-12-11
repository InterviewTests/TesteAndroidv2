package com.accenture.primo.bankapp.ui.login.contracts

import android.content.Context
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.login.LoginModel

interface ILoginActivity {
    fun getContext(): Context
    fun showLoading()
    fun hideLoading()
    fun onError(error: String)
    fun onSuccess(user: User)
    fun showPreferences(pref: LoginModel.LoginViewModel)
}

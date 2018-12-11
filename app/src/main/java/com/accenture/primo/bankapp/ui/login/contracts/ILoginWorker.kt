package com.accenture.primo.bankapp.ui.login.contracts

import android.content.Context
import com.accenture.primo.bankapp.ui.login.LoginModel
import io.reactivex.Observable

interface ILoginWorker {
    fun doLogin(request: LoginModel.LoginRequest): Observable<LoginModel.LoginResponse>
    fun readPreferences(context: Context): LoginModel.LoginViewModel?
    fun savePreferences(context: Context, pref: LoginModel.LoginViewModel)
}

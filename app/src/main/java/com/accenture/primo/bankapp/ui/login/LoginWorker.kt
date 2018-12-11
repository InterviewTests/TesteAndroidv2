package com.accenture.primo.bankapp.ui.login

import android.content.Context
import com.accenture.primo.bankapp.data.local.UserPreferences
import com.accenture.primo.bankapp.data.network.RemoteDataSource
import com.accenture.primo.bankapp.ui.login.contracts.ILoginWorker
import io.reactivex.Observable

class LoginWorker : ILoginWorker {
    override fun doLogin(request: LoginModel.LoginRequest): Observable<LoginModel.LoginResponse> {
        return RemoteDataSource.getService().doLogin(request.user, request.password);
    }

    override fun readPreferences(context: Context): LoginModel.LoginViewModel? {
        val objPreferences: UserPreferences = UserPreferences(context)
        return objPreferences.readPreferences()
    }

    override fun savePreferences(context: Context, pref: LoginModel.LoginViewModel) {
        UserPreferences(context).savePreferences(pref)
    }
}

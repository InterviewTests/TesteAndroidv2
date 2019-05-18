package com.br.projetotestesantanter.loginscreen

import android.content.Intent
import com.br.projetotestesantanter.api.model.LoginResponse
import java.lang.ref.WeakReference


internal interface LoginRouterInput{
    fun passDataListAccounts(loginResponse: LoginResponse) : Intent
    fun navegationListAccounts(intent: Intent)
}

class LoginRouter : LoginRouterInput {

    var activity: WeakReference<LoginActivity>? = null


    override fun passDataListAccounts(loginResponse: LoginResponse): Intent {

        return Intent()
    }

    override fun navegationListAccounts(intent: Intent) {


        activity?.get()?.startActivity(intent)

    }

}
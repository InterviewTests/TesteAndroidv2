package com.tata.bank.login

import java.lang.ref.WeakReference

interface LoginRouterInput {
//    fun determineNextScreen(position: Int): Intent
//    fun passDataToNextScene(position: Int, intent: Intent)
}

class LoginRouter: LoginRouterInput {

    lateinit var activity: WeakReference<LoginActivity>

}
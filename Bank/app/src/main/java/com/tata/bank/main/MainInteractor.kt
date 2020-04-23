package com.tata.bank.main

import android.os.Bundle
import android.util.Log
import com.tata.bank.login.UserAccount

interface MainInteractorInput {
    fun handleUserData(bundle: Bundle)
}

class MainInteractor: MainInteractorInput {

    lateinit var output: MainPresenterInput

    override fun handleUserData(bundle: Bundle) {
        val userAccount = bundle.getParcelable<UserAccount>("user_extra") as UserAccount
        Log.e("Carol", "")
    }
}
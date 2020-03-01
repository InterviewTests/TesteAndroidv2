package com.lucianogiardino.bankapp.login.domain.usecase

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lucianogiardino.bankapp.login.domain.model.User
import com.lucianogiardino.bankapp.login.domain.model.UserAccount

class SaveLoggedUserUseCase(context: Context) {

    private val context = context

    fun execute(userAccount: UserAccount){
        var privateMode = 0
        val prefName = "user"
        val sharedPref: SharedPreferences = context.getSharedPreferences(prefName, privateMode)
        var userAccountString = Gson().toJson(userAccount)
        sharedPref.edit().putString(prefName,userAccountString).apply()
        User.agency = userAccount.agency
        User.balance = userAccount.balance
        User.bankAccount = userAccount.bankAccount
        User.name = userAccount.name
        User.userId = userAccount.userId

    }
}
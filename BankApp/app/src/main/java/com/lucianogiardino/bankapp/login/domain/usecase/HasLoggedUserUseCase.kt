package com.lucianogiardino.bankapp.login.domain.usecase

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lucianogiardino.bankapp.login.domain.model.User
import com.lucianogiardino.bankapp.login.domain.model.UserAccount

class HasLoggedUserUseCase(context: Context) {

    private val context:Context = context

    fun execute(): Boolean{
        var privateMode = 0
        val prefName = "user"
        val sharedPref: SharedPreferences = context.getSharedPreferences(prefName, privateMode)
        return if (sharedPref.contains(prefName)){
            var userAccount = Gson().fromJson(sharedPref.getString(prefName,null), UserAccount::class.java)
            User.agency = userAccount.agency
            User.balance = userAccount.balance
            User.bankAccount = userAccount.bankAccount
            User.name = userAccount.name
            User.userId = userAccount.userId
            true
        }else{
            false
        }

    }
}
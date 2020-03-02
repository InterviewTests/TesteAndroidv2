package com.lucianogiardino.bankapp.domain.usecase

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lucianogiardino.bankapp.domain.model.User
import com.lucianogiardino.bankapp.domain.model.UserAccount

class HasLoggedUserUseCase(private val context: Context) {

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
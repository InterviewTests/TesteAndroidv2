package com.lucianogiardino.bankapp.domain.usecase

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lucianogiardino.bankapp.domain.model.User
import com.lucianogiardino.bankapp.domain.model.UserAccount
import com.lucianogiardino.bankapp.ui.login.LoginContract

class SaveLoggedUserUseCase(private val context: Context): LoginContract.UseCase.SaveLoggedUser {

    override fun execute(userAccount: UserAccount){
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
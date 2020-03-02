package com.lucianogiardino.bankapp.domain.usecase

import android.content.Context
import android.content.SharedPreferences
import com.lucianogiardino.bankapp.ui.statement.StatementContract


class LogoutUseCase(private val context: Context): StatementContract.UseCase.Logout {

    override fun execute(){
        var privateMode = 0
        val prefName = "user"
        val sharedPref: SharedPreferences = context.getSharedPreferences(prefName, privateMode)
        sharedPref.edit().clear().apply()

    }
}
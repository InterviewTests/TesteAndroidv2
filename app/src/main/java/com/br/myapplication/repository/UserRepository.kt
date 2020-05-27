package com.br.myapplication.repository

import com.br.myapplication.helper.AppHelper
import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.model.UserAccount

class UserRepository(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun saveUserPreferences(userAccount: UserAccount) {
        sharedPreferencesManager.saveUser(AppHelper.convertObjToString(userAccount))
    }
    fun getUser() : UserAccount? {
        val userString = sharedPreferencesManager.retrieveUser()
        return userString?.let {
            return AppHelper.convertStringToObj(it, UserAccount::class.java)
        }
    }

    fun deletePreferences() = sharedPreferencesManager.clearPreferences()
}
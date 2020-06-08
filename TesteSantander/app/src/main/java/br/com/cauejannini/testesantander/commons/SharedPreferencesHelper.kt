package br.com.cauejannini.testesantander.commons

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.cauejannini.testesantander.login.UserAccount
import com.google.gson.Gson

object SharedPreferencesHelper {

    private val SP_KEY_USER_ACCOUNT = "SP_KEY_USER_ACCOUNT"
    private val SP_KEY_USER_NAME = "SP_KEY_USER_NAME"

    fun getSP(context: Context): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveLoggedUser(context: Context, username: String) {
        getSP(context)?.edit()?.putString(SP_KEY_USER_NAME, username)?.apply()
    }

    fun getLastLoggedUser(context: Context): String? {
        return getSP(context)?.getString(SP_KEY_USER_NAME, null)
    }

//    fun saveUserAccount(context: Context, userAccount: UserAccount) {
//        val json = Gson().toJson(userAccount)
//        getSP(context)?.edit()?.putString(SP_KEY_USER_ACCOUNT, json)?.apply()
//    }
//
//    fun getUserAccount(context: Context): UserAccount? {
//        getSP(context)?.getString(SP_KEY_USER_ACCOUNT, null)?.let {
//           return Gson().fromJson(it, UserAccount::class.java)
//        }
//        return null
//    }

}
package br.com.ibm.teste.android.services.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 18:47
 */
object UserAccountPreferences {

    private const val NAME = "user_bank_account"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private val USER_SAVED = Pair("user_saved", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userSaved: String
        get() = preferences.getString(USER_SAVED.first, USER_SAVED.second)
        set(value) = preferences.edit {
            it.putString(USER_SAVED.first, value)
        }

    var userLoginData: String
        get() = preferences.getString(USER_SAVED.first, USER_SAVED.second)
        set(value) = preferences.edit {
            it.putString(USER_SAVED.first, value)
        }

}
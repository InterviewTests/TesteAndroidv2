package br.com.vinicius.bankapp.infra

import android.content.Context
import android.content.SharedPreferences
import br.com.vinicius.bankapp.domain.User

const val USERNAME = "username"
const val PASSWORD = "password"
const val NAME_PREFS = "user_preferences"

object SharedPreferences {

    private lateinit var sharedPreference: SharedPreferences

    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE)
    }



    private var user: User? = null

    fun saveUser(user: User) {
        this.user = user
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(USERNAME, user.username)
        editor.putString(PASSWORD, user.password)
        editor.apply()

        savedPreferences()
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun savedPreferences() {
        user.let {
            it?.username = sharedPreference.getString(USERNAME, "")
            it?.password = sharedPreference.getString(PASSWORD, "")
        }
    }

    fun getPreferences(): User? = if(isSave()) user else null


    fun isSave():Boolean {
        savedPreferences()
        return user != null
    }

    fun clearPreferences() {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}
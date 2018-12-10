package com.accenture.primo.bankapp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.accenture.primo.bankapp.ui.login.LoginModel
import com.google.gson.Gson

class UserPreferences(context: Context): IUserPreferences  {
    private val objGSon: Gson = Gson()
    private val PREFERENCES_KEY: String = "BANKAPP_PREFERENCES"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun readPreferences(): LoginModel.LoginViewModel? {
        val strJsonUser: String = sharedPreferences.getString(PREFERENCES_KEY, "")!!
        var objLoginViewModel: LoginModel.LoginViewModel? = null

        if (!strJsonUser.isNullOrEmpty())
            objLoginViewModel = objGSon.fromJson(strJsonUser, LoginModel.LoginViewModel::class.java)

        return objLoginViewModel
    }

    override fun savePreferences(pref: LoginModel.LoginViewModel) {
        val strJSon: String = objGSon.toJson(pref)

        val objSPEdit = sharedPreferences.edit()
        objSPEdit.clear()
        objSPEdit.putString(PREFERENCES_KEY, strJSon)
        objSPEdit.apply()
    }

}
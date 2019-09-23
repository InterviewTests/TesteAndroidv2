package com.example.santandertestev2.data

import android.content.Context
import android.content.SharedPreferences
import com.example.santandertestev2.domain.model.controller.UserAccount

class PreferenceData(private val context : Context) {

    private val pref : SharedPreferences
    init {
        pref = this.context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
    }

fun saveLogged(user : UserAccount){

    pref.edit().apply {
        putInt("agency", user.agency ?: 0 )
        putString("name", user.name)
        putLong("balance", user.balance?.toLong() ?: 0 )
        putInt("bankAccount", user.bankAccount ?: 0)
        commit()
    }
}

    fun getUser() : UserAccount?{
        pref.getString("name",null)?.let {

            val user = UserAccount().apply {
                name = pref.getString("name", null)
                bankAccount = pref.getInt("bankAccount", 0)
                balance = pref.getLong("balance", 0).toDouble()
                agency = pref.getInt("agency", 0)
            }
            return user
        }
        return null
    }

    fun clearData(){
        this.pref.edit().clear().commit()
    }

}
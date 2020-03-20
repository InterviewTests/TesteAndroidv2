package dev.ornelas.bankapp.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder

class SharedPreferenceDataSource(private val context: Context) {

    private  val PREFERENCES_NAME = "BANKAPP_PREFERENCES"


    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        getSharedPreferences().edit {
            putString(key, jsonString)
        }
    }

    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = getSharedPreferences().getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }


    fun getSharedPreferences(): SharedPreferences {
       return context .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

}
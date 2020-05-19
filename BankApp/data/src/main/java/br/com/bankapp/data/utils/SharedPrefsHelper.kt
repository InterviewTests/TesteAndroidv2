package br.com.bankapp.data.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject constructor(private val mSharedPreferences: SharedPreferences) {
    fun put(key: String?, value: String?) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    operator fun get(key: String?, defaultValue: String?): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    fun removeKey(key: String?) {
        mSharedPreferences.edit().remove(key).apply()
    }

    fun hasKey(key: String?): Boolean {
        return mSharedPreferences.contains(key)
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    companion object {
        const val PREF_USER = "app_default_pref"
    }

}
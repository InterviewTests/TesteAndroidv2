package com.santander.data.source.local.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.santander.data.BuildConfig
import com.santander.data.util.security.crypto.CryptoManager

class PreferencesManager(
    private val preferences: SharedPreferences,
    private val cryptographyManager: CryptoManager
) : IPreferencesManager {

    override fun <T> save(key: String, value: T) = with(preferences.edit()) {
        when (value) {
            is String -> putString(key, encrypt(key, value))
            is Int -> putString(key, encrypt(key, value.toString()))
            is Long -> putString(key, encrypt(key, value.toString()))
            is Boolean -> putString(key, encrypt(key, value.toString()))
            is Float -> putString(key, encrypt(key, value.toString()))
            else -> {
                putString(key, encrypt(key, Gson().toJson(value)))
            }
        }
        apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, clazz: Class<T>?, defaultValue: T?): T? {

        with(preferences) {
            val result: Any? = when (clazz) {
                String::class.java -> decrypt(key, getString(key, defaultValue as? String))
                Int::class.java -> decrypt(key, getString(key, defaultValue as? String))?.toInt()
                Long::class.java -> decrypt(key, getString(key, defaultValue as? String))?.toLong()
                Boolean::class.java -> decrypt(key, getString(key, defaultValue as? String))?.toBoolean()
                Float::class.java -> decrypt(key, getString(key, defaultValue as? String))?.toFloat()
                else -> {
                    return Gson().fromJson(decrypt(key, getString(key, null)), clazz)
                }
            }
            return result as? T
        }
    }

    override fun delete(key: String) = preferences.edit().remove(key).apply()

    override fun clear() = preferences.edit().clear().apply()

    override fun hasKey(key: String) = preferences.contains(key)

    private fun encrypt(key: String, value: String) = cryptographyManager.encrypt(value)
        .also {
            if (BuildConfig.DEBUG) {
                println("DATA: $it")
            }
        }

    private fun decrypt(key: String, value: String?) = value?.let { cryptographyManager.decrypt(it) }
        .also {
            if (BuildConfig.DEBUG) {
                println("DATA: $it")
            }
        }

}
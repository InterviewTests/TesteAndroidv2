package com.accenture.santander.dataManager.Storag

import android.content.Context
import com.orhanobut.hawk.Hawk

/**
 * Created by dev on 10/05/2018.
 */
class StoragManager(private val context: Context) {

    companion object {
        @JvmField
        val LOGIN = "TLOOKGEINN"

        @JvmField
        val PASSWORD = "TPOAKSESNWSOTDR"
    }

    init {
        Hawk.init(context).build()
    }

    fun setPreferences(key: String, value: String?) {
        value?.let {
            Hawk.put(key, value)
        }
    }

    fun getPreferences(key: String): String {
        return Hawk.get(key, "")
    }

    fun cleanPreferences() {
        Hawk.deleteAll()
    }
}
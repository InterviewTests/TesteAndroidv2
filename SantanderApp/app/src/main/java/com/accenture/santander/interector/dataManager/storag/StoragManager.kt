package com.accenture.santander.interector.dataManager.storag

import android.app.Activity
import com.orhanobut.hawk.Hawk

/**
 * Created by dev on 10/05/2018.
 */
class StoragManager(context: Activity) : IStoragManager {

    companion object {
        @JvmField
        val LOGIN = "TLOOKGEINN"
    }

    init {
        Hawk.init(context).build()
    }

    override fun setPreferences(key: String, value: String?) {
        value?.let {
            Hawk.put(key, value)
        }
    }

    override fun getPreferences(key: String): String {
        return Hawk.get(key, "")
    }

    override fun cleanPreferences() {
        Hawk.deleteAll()
    }
}
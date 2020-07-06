package br.com.mdr.testeandroid.extensions

import android.util.Log
import br.com.mdr.testeandroid.util.Constants.Companion.LOG_TAG
import com.google.gson.Gson
import java.io.Reader

inline fun <reified T> Gson.fromJsonOrNull(json: String): T? {
    return try {
        fromJson(json, T::class.java)
    } catch (e: Throwable) {
        null
    }
}


inline fun <reified T> Gson.fromJsonOrNull(json: Reader): T? {
    return try {
        fromJson(json, T::class.java)
    } catch (e: Throwable) {
        e.localizedMessage?.let { e.apply { Log.e(LOG_TAG, it) } }
        null
    }
}

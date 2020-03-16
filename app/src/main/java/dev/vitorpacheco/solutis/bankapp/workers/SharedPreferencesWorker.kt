package dev.vitorpacheco.solutis.bankapp.workers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dev.vitorpacheco.solutis.bankapp.BankApp

data class SharedPreferencesRequest(
    val context: Context,
    val key: String,
    val value: String? = null
)

interface SharedPreferencesWorkerInput {
    fun save(request: SharedPreferencesRequest)
    fun get(request: SharedPreferencesRequest): String?
}

class SharedPreferencesWorker : SharedPreferencesWorkerInput {

    override fun save(request: SharedPreferencesRequest) {
        request.context.getSharedPreferences(BankApp.SHARED_PREFERENCES_KEY, MODE_PRIVATE).edit()
            .putString(request.key, request.value).apply()
    }

    override fun get(request: SharedPreferencesRequest): String? {
        return request.context.getSharedPreferences(BankApp.SHARED_PREFERENCES_KEY, MODE_PRIVATE)
            .getString(request.key, null)
    }

}
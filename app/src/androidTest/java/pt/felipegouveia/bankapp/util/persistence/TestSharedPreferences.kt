package pt.felipegouveia.bankapp.util.persistence

import android.content.Context
import javax.inject.Inject

class TestSharedPreferences @Inject constructor(
    private val context: Context
): BankSharedPreference {

    override fun readDecryptedString(key: String): String? {
        return context.getSharedPreferences(
            BankSharedPreferences.BANK_PREFERENCES, Context.MODE_PRIVATE
        ).getString(key, null)
    }

    override fun saveEncryptedString(key: String, data: String) {
        context.getSharedPreferences(BankSharedPreferences.BANK_PREFERENCES, Context.MODE_PRIVATE)
            .edit()
            .putString(key, data)
            .apply()
    }
}
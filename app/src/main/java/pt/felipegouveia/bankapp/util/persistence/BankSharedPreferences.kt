package pt.felipegouveia.bankapp.util.persistence

import android.content.Context
import pt.felipegouveia.bankapp.util.security.CipherWrapper
import pt.felipegouveia.bankapp.util.security.KeyStoreWrapper
import java.security.KeyPair
import javax.inject.Inject

class BankSharedPreferences @Inject constructor(
    private val context: Context,
    private val cipher: CipherWrapper,
    private val keyStoreWrapper: KeyStoreWrapper
): BankSharedPreference {

    private var prefsKey: KeyPair? = null

    init {
        prefsKey = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(KeyStoreWrapper.SHARED_PREFS_KEY)
        if(prefsKey == null) prefsKey = keyStoreWrapper.createAndroidKeyStoreAsymmetricKey(KeyStoreWrapper.SHARED_PREFS_KEY)
    }

    override fun readDecryptedString(key: String): String? {
        return cipher.decrypt(
            context.getSharedPreferences(
                BANK_PREFERENCES, Context.MODE_PRIVATE
            ).getString(key, null),
            prefsKey?.private
        )
    }

    override fun saveEncryptedString(key: String, data: String) {
        context.getSharedPreferences(BANK_PREFERENCES, Context.MODE_PRIVATE)
            .edit()
            .putString(key, cipher.encrypt(data, prefsKey?.public))
            .apply()
    }

    companion object {
        const val BANK_PREFERENCES = "PREFS_LOGIN"
    }

}
package pt.felipegouveia.bankapp.util.persistence

interface BankSharedPreference {

    fun readDecryptedString(key: String): String?

    fun saveEncryptedString(key: String, data: String)

}
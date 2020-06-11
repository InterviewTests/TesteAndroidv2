package projects.kevin.bankapp.user.sharedPref

import android.content.Context
import android.content.SharedPreferences

//val userId: Long? = 0,
//val name: String? = "",
//val bankAccount: String? = "",
//val agency: String? = "",
//val balance: BigDecimal? = BigDecimal.ZERO


class UserDataSharedPref(private val context: Context) {

    companion object {
        private const val BANK_ACCOUNT = "BANK_ACCOUNT"
        private const val AGENCY = "AGENCY"
        private const val USER_ID = "USER_ID"
        private const val BALANCE = "BALANCE"
        private const val NAME = "NAME"
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences("userData", Context.MODE_PRIVATE)!!
    }

    private fun getPreferencesEdit(): SharedPreferences.Editor {
        return getPreferences().edit()
    }

    fun clearPreferences() {
        val editor = getPreferencesEdit()
        editor.clear()
        editor.apply()
    }

    ///SET
    fun persistUserId(userId: String) {
        getPreferencesEdit().putString(USER_ID, userId).commit()
    }

    fun persistName(name: String) {
        getPreferencesEdit().putString(NAME, name).commit()
    }

    fun persistBankAccount(bankAccount: String) {
        getPreferencesEdit().putString(BANK_ACCOUNT, bankAccount).commit()
    }

    fun persistAgency(agency: String) {
        getPreferencesEdit().putString(AGENCY, agency).commit()
    }

    fun persistBalance(balance: String) {//BigDecimal bd = new BigDecimal("10.0001");
        getPreferencesEdit().putString(BALANCE, balance).commit()
    }

    ///GET
    fun getUserId(): String? {
        return getPreferences().getString(USER_ID, null)
    }

    fun getName(): String? {
        return getPreferences().getString(NAME, null)
    }

    fun getBankAccount(): String? {
        return getPreferences().getString(BANK_ACCOUNT, null)
    }

    fun getAgency(): String? {
        return getPreferences().getString(AGENCY, null)
    }

    fun getBalance(): String? {
        return getPreferences().getString(BALANCE, null)
    }

}
package br.com.flaviokreis.santanderv2.data.preferences

import android.content.SharedPreferences
import br.com.flaviokreis.santanderv2.data.model.UserAccount
import com.google.gson.Gson
import javax.inject.Inject

class LoginPreference @Inject constructor(
    private val preferences: SharedPreferences
) {

    companion object {
        private const val USER_ACCOUNT_KEY = "user_account"
    }

    fun clear() {
        preferences.edit()
            .remove(USER_ACCOUNT_KEY)
            .clear()
            .apply()
    }

    fun hasUserAccount(): Boolean = preferences.contains(USER_ACCOUNT_KEY)

    fun getUserAccount(): UserAccount? {
        val json = preferences.getString(USER_ACCOUNT_KEY, "")
        return Gson().fromJson<UserAccount>(json, UserAccount::class.java)
    }

    fun saveUserAccount(userAccount: UserAccount?) {
        val json = Gson().toJson(userAccount)

        preferences.edit()
            .putString(USER_ACCOUNT_KEY, json)
            .apply()
    }

}
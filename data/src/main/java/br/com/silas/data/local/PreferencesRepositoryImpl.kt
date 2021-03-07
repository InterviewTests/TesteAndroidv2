package br.com.silas.data.local

import android.content.SharedPreferences
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Completable

class PreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    PreferencesRepository {
    companion object {
        const val USER_ID = "user_id"
        const val USER_NAME = "name"
        const val BANCK_ACCOUNT = "bank_account"
        const val AGENCY = "agency"
        const val BALANCE = "balance"
    }

    override fun save(user: User?): Completable {

        return Completable.create { emitter ->
            if (user?.id != null) {
                val preferencesEditor = sharedPreferences.edit()
                preferencesEditor.putInt(USER_ID, user.id)
                preferencesEditor.putString(USER_NAME, user.name)
                preferencesEditor.putString(BANCK_ACCOUNT, user.bankAccount)
                preferencesEditor.putString(AGENCY, user.agency)
                preferencesEditor.putFloat(BALANCE, user.balance.toFloat())

                preferencesEditor.apply()
                emitter.onComplete()
            }

            emitter.onComplete()
        }
    }

    override fun getUser() =
        User(
            sharedPreferences.getInt(USER_ID, 0),
            sharedPreferences.getString(USER_NAME, ""),
            sharedPreferences.getString(BANCK_ACCOUNT, ""),
            sharedPreferences.getString(AGENCY, ""),
            sharedPreferences.getFloat(BALANCE, 2.2.toFloat()).toDouble()
        )
}

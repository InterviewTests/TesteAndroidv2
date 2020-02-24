package br.com.flaviokreis.santanderv2.data.repositories

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.flaviokreis.santanderv2.data.network.BankService
import br.com.flaviokreis.santanderv2.data.response.LoginResponse
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val bankService: BankService
) {

    private val userAccountKey = "user_account"

    fun login(user: String, password: String): LiveData<LoginResponse> {
        val result = MediatorLiveData<LoginResponse>()
        val request = bankService.login(user, password)
        result.addSource(request) {
            it.response?.body()?.let { loginResponse ->
                saveUserAccount(loginResponse)
                result.value = loginResponse
                result.removeSource(request)
            }
        }

        return result
    }

    private fun saveUserAccount(response: LoginResponse) {
        if (response.error == null || response.error.code == 0) {
            val json = Gson().toJson(response.userAccount)

            preferences.edit()
                .putString(userAccountKey, json)
                .apply()
        }
    }

}
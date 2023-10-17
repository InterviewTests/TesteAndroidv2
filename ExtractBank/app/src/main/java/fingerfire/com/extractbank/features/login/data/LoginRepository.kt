package fingerfire.com.extractbank.features.login.data

import android.content.SharedPreferences
import fingerfire.com.extractbank.api.BankApi
import fingerfire.com.extractbank.model.Login
import fingerfire.com.extractbank.model.User
import fingerfire.com.extractbank.network.ServiceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(
    private val bankApi: BankApi,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun login(login: Login): ServiceState<User> {
        return withContext(Dispatchers.IO) {
            val response = bankApi.login(login)
            if (response.isSuccessful) {
                saveUser(login)
                ServiceState.Success(response.body())
            } else {
                ServiceState.Error()
            }
        }
    }

    private fun saveUser(login: Login) {
        sharedPreferences.edit().putString("USER_LOGIN", login.user).apply()
    }

    fun getSavedUser(): String? {
        return sharedPreferences.getString("USER_LOGIN", "")
    }
}
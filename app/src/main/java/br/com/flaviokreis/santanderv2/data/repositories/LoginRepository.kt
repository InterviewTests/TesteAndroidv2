package br.com.flaviokreis.santanderv2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.flaviokreis.santanderv2.data.model.UserAccount
import br.com.flaviokreis.santanderv2.data.network.BankService
import br.com.flaviokreis.santanderv2.data.preferences.LoginPreference
import br.com.flaviokreis.santanderv2.data.response.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val loginPreference: LoginPreference,
    private val bankService: BankService
) {

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

    fun logout() {
        loginPreference.clear()
    }

    fun hasUserAccount(): LiveData<Boolean> {
        val userLiveData = MutableLiveData<Boolean>()

        userLiveData.value = loginPreference.hasUserAccount()

        return userLiveData
    }

    fun getUserAccount(): LiveData<UserAccount> {
        val userLiveData = MutableLiveData<UserAccount>()

        userLiveData.value = loginPreference.getUserAccount()

        return userLiveData
    }

    private fun saveUserAccount(response: LoginResponse) {
        if (response.error == null || response.error.code == 0) {
            loginPreference.saveUserAccount(response.userAccount)
        }
    }
}
package com.felipemsa.idletime.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.felipemsa.idletime.data.BankApi
import com.felipemsa.idletime.data.LoginResponse
import com.felipemsa.idletime.data.UserAccount
import com.felipemsa.idletime.helper.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    val loginData = MutableLiveData<UserAccount>()
    val loginError = MutableLiveData<String>()
    val buttonState = MutableLiveData<Boolean>()

    fun login(user: String, pass: String) {
        BankApi().banckApi().login(user, pass).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loginData.postValue(it.userAccount)
                        DataStorage.setAccount(it.userAccount)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginError.postValue(t.localizedMessage)
            }
        })
    }

}
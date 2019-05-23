package com.felipemsa.idletime.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.BankApi
import com.felipemsa.idletime.data.DataStorage
import com.felipemsa.idletime.data.LoginResponse
import com.felipemsa.idletime.isValidPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val saveUser = MutableLiveData<String>()
    val loginError = MutableLiveData<Int>()
    val buttonEnabled = MutableLiveData<Boolean>()

    fun login(user: String, pass: String, saveUserData: Boolean) {

        if (pass.isValidPassword())
            BankApi().banckApi().login(user, pass).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        if (saveUserData)
                            DataStorage.saveUser(user)

                        saveUser.postValue(user)
                        DataStorage.setAccount(response.body()!!.userAccount)
                    } else loginError.postValue(R.string.message_cant_login)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginError.postValue(R.string.warning_error_login)
                }
            })
        else {
            loginError.postValue(R.string.message_invalid_password)
        }
    }

}
package com.example.testeandroidv2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeandroidv2.data.ApiService
import com.example.testeandroidv2.model.LoginBodyResponse
import com.example.testeandroidv2.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val loginValue: MutableLiveData<LoginBodyResponse> = MutableLiveData()

    fun login(user: User){

        ApiService.service.login(user).enqueue(object: Callback<LoginBodyResponse> {
            override fun onResponse(call: Call<LoginBodyResponse>, response: Response<LoginBodyResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        loginValue.value = loginResponse
                    }
                }
            }

            override fun onFailure(call: Call<LoginBodyResponse>, t: Throwable) { }
        })

    }

}
package com.joaoneto.testeandroidv2.loginscreen.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaoneto.testeandroidv2.loginscreen.model.LoginResponseModel
import com.joaoneto.testeandroidv2.loginscreen.service.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoginViewModel @Inject constructor(private var loginService: LoginService) :
        ViewModel() {


    fun signIn(user: String, password: String): LiveData<LoginResponseModel> {

        val loginResponse = MutableLiveData<LoginResponseModel>()

        loginService.signIn(user, password).enqueue(object : Callback<LoginResponseModel> {
            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {

                Log.i("--->", t.message!!)
            }

            override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
            ) {

                if (response.code() == 200) {
                    loginResponse.value = response.body()
                } else {
                    Log.i("--->", response.code().toString())
                    Log.i("--->", response.message())
                }

            }

        })

        return loginResponse
    }
}





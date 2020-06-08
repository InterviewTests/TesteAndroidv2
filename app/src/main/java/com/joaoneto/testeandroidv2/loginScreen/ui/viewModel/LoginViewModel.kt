package com.joaoneto.testeandroidv2.loginScreen.ui.viewModel


import RetrofitInitializer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaoneto.testeandroidv2.loginScreen.model.LoginResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel :
        ViewModel() {


    fun signIn(user: String, password: String): LiveData<LoginResponseModel> {

        val loginResponse = MutableLiveData<LoginResponseModel>()

        RetrofitInitializer().loginService().signIn(user, password).enqueue(object : Callback<LoginResponseModel> {
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





package com.example.androidtest.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.data.api.ApiClient
import com.example.androidtest.data.api.ResponseEntity
import com.example.androidtest.domain.entities.LoginEntity
import com.example.androidtest.domain.repositories.LoginRepository
import com.example.androidtest.utils.Constants
import com.example.androidtest.utils.Mask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var mToastNotification = MutableLiveData<String>()
    private val mLoginRepository = ApiClient.createService(LoginRepository::class.java)
    private val mName = MutableLiveData<String>()
    private val mCont = MutableLiveData<String>()

    fun login(): LiveData<String> {
        return mToastNotification
    }

    fun nameUser(): LiveData<String> {
        return mName
    }

    fun contUser(): LiveData<String> {
        return mCont
    }

    fun doLogin(user: String, password: String) {

        val loginEntity = LoginEntity(user, password)
        val call: Call<ResponseEntity> = mLoginRepository.getLogin(loginEntity)

        call.enqueue(object : Callback<ResponseEntity> {
            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {
                val message = t.message
                mToastNotification.value = message
            }

            override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                val res = response.body()
                if (res!!.error["message"] != null) {
                    mToastNotification.value = res.error["message"].toString()
                } else {
                    val agencyMask = Mask.addMask(Constants.CONT_MASK, res.userAccount["agency"].toString())
                    val agencyConcat = res.userAccount["bankAccount"].toString() + " / " + agencyMask

                    mName.value = res.userAccount["name"].toString().replace("\"", "")
                    mCont.value = agencyConcat.replace("\"", "")

                    mToastNotification.value = "Success"
                }
            }

        })

    }

}
package com.example.androidtest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.data.api.ApiClient
import com.example.androidtest.data.api.ResponseEntity
import com.example.androidtest.domain.entities.LoginEntity
import com.example.androidtest.domain.entities.StatementsEntity
import com.example.androidtest.domain.repositories.HomeRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type;
import java.text.DecimalFormat

class HomeViewModel : ViewModel() {

    private var mStatements = MutableLiveData<List<StatementsEntity>>()
    private val mHomeRepository = ApiClient.createService(HomeRepository::class.java)
    private var mToastNotification = MutableLiveData<String>()
    private var mSaldo = MutableLiveData<String>()
    private var mConta = MutableLiveData<String>()
    private var mNome = MutableLiveData<String>()
    private val dec = DecimalFormat("#,###.00")

    init {
        listStatements()
    }

    fun list(): LiveData<List<StatementsEntity>> {
        return mStatements
    }

    fun saldo(): LiveData<String> {
        return mSaldo
    }

    fun listStatements() {
        val loginEntity = LoginEntity("user", "password")
        val call: Call<ResponseEntity> = mHomeRepository.getList()

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
                    val listStatements = res.statementList
                    val gsonn = Gson()
                    val type: Type = object : TypeToken<List<StatementsEntity?>?>() {}.type
                    val statementList: List<StatementsEntity> = gsonn.fromJson(listStatements, type)

                    var total = 0.0
                    statementList.forEach {
                        total += it.value
                    }
                    mSaldo.value = "R$ " + dec.format(total)

                    mStatements.value = statementList
                }
            }

        })
    }

    fun toastNotification(): LiveData<String> {
        return mToastNotification
    }


}
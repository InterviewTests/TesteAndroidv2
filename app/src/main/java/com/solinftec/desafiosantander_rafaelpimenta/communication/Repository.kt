package com.solinftec.desafiosantander_rafaelpimenta.communication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.Statement
import com.solinftec.desafiosantander_rafaelpimenta.util.Helper

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class Repository {

    private val responseStatements = MutableLiveData<List<Statement>>()
    private val loginResponse = MutableLiveData<LoginResponse>()


    fun listAll(success: (List<Statement>) -> Unit, failure: () -> Unit) {
        val list: MutableList<Statement> = mutableListOf()
        ApiData().getStatements().enqueue(object : Callback<Map<String, Any>> {
            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                failure()
            }
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<Map<String, Any>>,
                response: Response<Map<String, Any>>
            ) {
                val listAny = response.body()?.get("statementList") as List<Map<String, Any>>
                listAny.forEach {
                    val valFormatted = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                        .format(it["value"].toString().toDouble())
                    val date = Helper().dateFormat(it["date"].toString())
                    list.add(
                        Statement(
                            it["title"].toString(),
                            it["desc"].toString(),
                            date,
                            valFormatted,
                            it["value"].toString().toDouble()
                        )
                    )
                }
                success(list)
            }
        })
    }


    fun login(loginData: Map<String, String>): LiveData<LoginResponse> {
        ApiData().login(loginData).enqueue(object : Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (!response.isSuccessful) {
                    return
                }
                loginResponse.value = response.body()
            }
        })
        return loginResponse
    }
}
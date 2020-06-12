package com.appdesafioSantander.bank.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdesafioSantander.services.ApiData
import com.appdesafioSantander.bank.model.Login
import com.appdesafioSantander.bank.model.Statement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class Repository {
    private val responseStatements = MutableLiveData<List<Statement>>()
    private val loginResponse = MutableLiveData<Login>()


    private fun dateFormat(date: String): String {
        val s = date.split("-")
        return "${s[2]}/${s[1]}/${s[0]}"
    }

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
                    val date = dateFormat(it["date"].toString())
                    list.add(
                        Statement(
                            it["title"].toString(),
                            it["desc"].toString(),
                            date,
                            "$valFormatted",
                            it["value"].toString().toDouble()
                        )
                    )
                }
                success(list)
            }
        })
    }


    fun login(loginData: Map<String, String>): LiveData<Login> {
        ApiData().login(loginData).enqueue(object : Callback<Login> {

            override fun onFailure(call: Call<Login>, t: Throwable) {}
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (!response.isSuccessful) {
                    return
                }
                loginResponse.value = response.body()!!
            }
        })
        return loginResponse
    }
}

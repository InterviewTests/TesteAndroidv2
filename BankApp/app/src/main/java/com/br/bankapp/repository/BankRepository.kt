package com.br.bankapp.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.bankapp.api.BankApi
import com.br.bankapp.model.LoginResponse
import com.br.bankapp.model.Statement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class BankRepository {

    private val responseStatements = MutableLiveData<List<Statement>>()
    private val loginResponse = MutableLiveData<LoginResponse>()

    fun listAll(success: (List<Statement>) -> Unit, failure: () -> Unit) {
        val list: MutableList<Statement> = mutableListOf()
        BankApi().getStatements().enqueue(object : Callback<Map<String, Any>> {
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
                    val valFormatted = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(it["value"].toString().toDouble())
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

    private fun dateFormat(date: String): String{
        val s = date.split("-")
        return "${s[2]}/${s[1]}/${s[0]}"
    }

    fun login(loginData: Map<String, String>): LiveData<LoginResponse> {
        BankApi().login(loginData).enqueue(object : Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (!response.isSuccessful) {
                    Log.e("response", "response is not successful")
                    return
                }
                loginResponse.value = response.body()!!
            }
        })
        return loginResponse
    }

}
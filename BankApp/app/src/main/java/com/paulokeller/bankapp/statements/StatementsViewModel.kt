package com.paulokeller.bankapp.statements

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulokeller.bankapp.models.AppState
import com.paulokeller.bankapp.models.Statements
import com.paulokeller.bankapp.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StatementsViewModel @Inject constructor(private val context: Context, private val client: Client) : ViewModel() {
    val statementsState: MutableLiveData<AppState<Statements>> = MutableLiveData()

    fun fetchStatements() {
        try {
            client.fetchStatements(1)!!.enqueue(object : Callback<Statements?> {
                override fun onFailure(call: Call<Statements?>, t: Throwable?) {
                    statementsState.value = AppState<Statements>(t?.message, null)
                }

                override fun onResponse(call: Call<Statements?>, response: Response<Statements?>) {
                    println(response.toString())
                    if (response.body() != null) {
                        println(response.body().toString())
                        statementsState.value = AppState<Statements>(null, response.body())
                    }
                }
            })
        } catch (ex: Exception) {
            statementsState.value = AppState<Statements>("Fail to execute request", null)
        }
    }
}

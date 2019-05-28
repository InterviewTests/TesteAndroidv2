package com.felipemsa.idletime.ui.statements

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.felipemsa.idletime.data.BankApi
import com.felipemsa.idletime.data.Statement
import com.felipemsa.idletime.data.StatementsResponse
import com.felipemsa.idletime.data.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementsViewModel: ViewModel() {

    var statementsList = MutableLiveData<List<Statement>>()

    fun getStatements() {
        BankApi().banckApi().statements(DataStorage.getUserId()).enqueue(object: Callback<StatementsResponse> {
            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                if (response.isSuccessful) {
                    val list = response.body()!!.statementList.sortedByDescending { it.date }
                    statementsList.postValue(list)
                }
            }

            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {

            }
        })
    }

}
package com.example.testeandroidv2.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeandroidv2.data.ApiService
import com.example.testeandroidv2.model.Statement
import com.example.testeandroidv2.model.StatementsBodyResponse
import retrofit2.Call
import retrofit2.Response

class StatementsViewModel : ViewModel() {

    val statementsLiveData: MutableLiveData<List<Statement>> = MutableLiveData()

    fun getStatements() {

        ApiService.service.getStatements().enqueue(object: retrofit2.Callback<StatementsBodyResponse>{
            override fun onResponse(call: Call<StatementsBodyResponse>, response: Response<StatementsBodyResponse>) {
                if (response.isSuccessful){
                    val bookDetails: MutableList<Statement> = mutableListOf()
                    response.body()?.let { bookDetailsResponse ->
                        for (result in bookDetailsResponse.statementList) {
                            bookDetails.add(result)
                        }
                    }
                    statementsLiveData.value = bookDetails
                }
            }

            override fun onFailure(call: Call<StatementsBodyResponse>, t: Throwable) {
                val x = "a"
            }
        })
    }

}
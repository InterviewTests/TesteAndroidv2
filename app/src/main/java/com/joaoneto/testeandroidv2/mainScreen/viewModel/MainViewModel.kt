package com.joaoneto.testeandroidv2.mainScreen.viewModel


import RetrofitInitializer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaoneto.testeandroidv2.mainScreen.model.StatementsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun getStatements(): LiveData<StatementsResponseModel> {

        val statementResponse = MutableLiveData<StatementsResponseModel>()

        RetrofitInitializer().statementsService().getStatements()
            .enqueue(object : Callback<StatementsResponseModel> {
                override fun onFailure(call: Call<StatementsResponseModel>, t: Throwable) {
                    Log.e("--->", t.message!!)
                }

                override fun onResponse(
                    call: Call<StatementsResponseModel>,
                    response: Response<StatementsResponseModel>
                ) {
                    if (response.code() == 200) {

                        statementResponse.value = response.body()

                    } else {
                        Log.i("--->", response.code().toString())
                        Log.i("--->", response.message())
                    }

                }

            })

        return statementResponse
    }
}
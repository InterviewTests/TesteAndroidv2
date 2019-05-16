package com.example.projetobank.data.source.remote

import android.util.Log
import com.example.projetobank.data.model.DadosBancarioResposta
import com.example.projetobank.data.model.Statement
import com.example.projetobank.data.model.statementList
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.DadosBancarioDataSource
import com.example.projetobank.data.source.RetrofitInicializador
import com.example.projetobank.util.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DadosBancarioRemoteDataSource

    ( val appExecutors: AppExecutors)

    : DadosBancarioDataSource{

    override fun pegaDadosBancario(
            concentrador: Int,
        callbackResponse: CallbackResponse<statementList>
    ) {

        appExecutors.networkIO.execute {
            concentrador.let { concentrador ->
                RetrofitInicializador()
                    .dadosBancarioService().requestDadosBancario(1)
                    .enqueue(object : Callback<statementList> {
                        override fun onResponse(
                            call: Call<statementList>, response: Response<statementList>?
                        ) {
                            response?.let {
                                it.body()?.let { dadosBancario ->
                                    appExecutors.mainThread.execute {
                                        Log.e("sucesso ", "objeto  : " + dadosBancario.statementList[0])
                                        callbackResponse.sucesso(dadosBancario)
                                    }
                                }
                                if (it.body() == null) {
                                    callbackResponse.erro()
                                }
                            }
                        }

                        override fun onFailure(call: Call<statementList?>?, t: Throwable?) {
                            appExecutors.mainThread.execute {
                                Log.e("ERROResposta ", "erro")
                                callbackResponse.erro()
                            }
                        }
                    })
            }
        }
    }


    override fun salvaDadosStatement(statement: Statement) {
    }

    companion object {
        private lateinit var INSTANCE: DadosBancarioRemoteDataSource
        private var needsNewInstance = true

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): DadosBancarioRemoteDataSource {
            if (needsNewInstance) {
                INSTANCE = DadosBancarioRemoteDataSource(appExecutors)
                needsNewInstance = false
            }
            return INSTANCE
        }
    }
}
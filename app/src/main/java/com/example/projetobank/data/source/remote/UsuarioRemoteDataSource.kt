package com.example.projetobank.data.source.remote

import android.util.Log
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.RetrofitInicializador
import com.example.projetobank.data.source.UsuarioDataSource
import com.example.projetobank.util.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRemoteDataSource
    ( val appExecutors: AppExecutors)
    : UsuarioDataSource {


    override fun pegaUsuario(
        concentrador: Usuario?,
        callbackResponse: CallbackResponse<UsuarioResposta>)
    {
        appExecutors.networkIO.execute {
            concentrador?.let { concentrador ->
                RetrofitInicializador()
                    .usuarioService().requestDadosBancario(concentrador)
                    .enqueue(object : Callback<UsuarioResposta> {
                        override fun onResponse(
                            call: Call<UsuarioResposta?>?, response: Response<UsuarioResposta?>?
                        ) {
                            response?.let {
                                it.body()?.let { usuarioResposta ->
                                    appExecutors.mainThread.execute {
                                        Log.e("sucessoResposta ",usuarioResposta.userAccount.get(0).name + "yeey")
                                        callbackResponse.sucesso(usuarioResposta)
                                    }
                                }
                                if (it.body() == null) {
                                    callbackResponse.erro()
                                }
                            }
                        }

                        override fun onFailure(call: Call<UsuarioResposta?>?, t: Throwable?) {
                            appExecutors.mainThread.execute {
                                callbackResponse.erro()
                            }
                        }
                    })
            }
        }    }

    override fun deletaUsuario(acao: () -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun salvaDadosDeAutenticacao(
        usuario: Usuario?,
        userAccount: userAccount,
        sucesso: () -> Unit,
        erro: () -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        private lateinit var INSTANCE: UsuarioRemoteDataSource
        private var needsNewInstance = true

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): UsuarioRemoteDataSource {
            if (needsNewInstance) {
                INSTANCE = UsuarioRemoteDataSource(appExecutors)
                needsNewInstance = false
            }
            return INSTANCE
        }
    }

}
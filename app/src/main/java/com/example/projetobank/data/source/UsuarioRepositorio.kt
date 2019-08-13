package com.example.projetobank.data.source

import android.util.Log
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.source.remote.UsuarioRemoteDataSource

class UsuarioRepositorio
    (
    private val localDataSource: UsuarioDataSource,
    private val remoteDataSource: UsuarioRemoteDataSource
) : UsuarioDataSource {

    private lateinit var user : Usuario
    override fun pegaUsuario(concentrador: Usuario?, callbackResponse: CallbackResponse<UsuarioResposta>) {
        concentrador?.let {
            remoteDataSource.pegaUsuario(concentrador, object : CallbackResponse<UsuarioResposta> {
                override fun sucesso(response: UsuarioResposta) {
                    user = concentrador
                    Log.e("sucessoRepositorio : ", "nome  : " + response.userAccount.name)
                    salvaDadosDeAutenticacao(
                            user,
                        response.userAccount,
                        { callbackResponse.sucesso(response) },
                        { callbackResponse.erro() }
                    )
                }
                override fun erro() {
                    callbackResponse.erro()
                }
            })
            return
        }
       localDataSource.pegaUsuario(null, callbackResponse)
    }

    override fun pegaUsuarioLogado(callbackResponse: CallbackResponse<Usuario>) {
        localDataSource.pegaUsuarioLogado(callbackResponse)
    }

    override fun deletaUsuario(acao: () -> Unit) {
        localDataSource.deletaUsuario { acao() }
    }

    override fun salvaDadosDeAutenticacao(
        usuario: Usuario?,
        userAccount: userAccount,
        sucesso: () -> Unit,
        erro: () -> Unit
    ) {
        Log.e("sucessooo ", userAccount.name)

        localDataSource.salvaDadosDeAutenticacao(usuario, userAccount, sucesso, erro)
    }

    companion object {

        private var INSTANCE: UsuarioRepositorio? = null

        @JvmStatic
        fun getInstance(
            usuarioLocalDataSource: UsuarioDataSource,
            usuarioRemoteDataSource: UsuarioRemoteDataSource
        ): UsuarioRepositorio {
            return INSTANCE ?: UsuarioRepositorio(usuarioLocalDataSource, usuarioRemoteDataSource)
                .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
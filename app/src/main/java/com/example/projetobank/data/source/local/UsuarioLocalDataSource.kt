package com.example.projetobank.data.source.local

import android.support.annotation.VisibleForTesting
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.UsuarioDataSource
import com.example.projetobank.util.AppExecutors

class UsuarioLocalDataSource(
    val appExecutors: AppExecutors,
    val usuarioDao: UsuarioDao

) : UsuarioDataSource {


    override fun pegaUsuario(concentrador: Usuario?, callbackResponse: CallbackResponse<userAccount>) {
        appExecutors.diskIO.execute {
            val usuarios = usuarioDao.pegarUsuario()
           // val usuarioResposta = UsuarioResposta(usuario = usuarios)
            appExecutors.mainThread.execute {
                if (usuarios.isEmpty()) {
                    callbackResponse.erro()
                } else {
                   // callbackResponse.sucesso(usuarioResposta)
                }
            }
        }
    }

    override fun deletaUsuario(acao: () -> Unit) {
        appExecutors.diskIO.execute {
            try {
                usuarioDao.deletarUsuario()
                appExecutors.mainThread.execute(acao)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun salvaDadosDeAutenticacao(
        usuario: Usuario?,
        userAccount: userAccount,
        sucesso: () -> Unit,
        erro: () -> Unit
    ) {
        appExecutors.diskIO.execute {
            try {
                usuarioDao.deletarUsuario()
                usuarioDao.adicionarLogin(usuario)
                appExecutors.mainThread.execute(sucesso)

            } catch (e: Exception){
                e.printStackTrace()
                appExecutors.mainThread.execute(erro)
            }
        }
    }

    companion object {
        private var INSTANCE: UsuarioLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            usuarioDao: UsuarioDao
        ): UsuarioLocalDataSource {
            if (INSTANCE == null) {
                synchronized(UsuarioLocalDataSource::javaClass) {
                    INSTANCE = UsuarioLocalDataSource(appExecutors, usuarioDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}

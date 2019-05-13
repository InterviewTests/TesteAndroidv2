package com.example.projetobank.ui.login

import android.util.Log
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.UsuarioRepositorio

class LoginPresenter

    (var repositorio: UsuarioRepositorio,
     val fragment: LoginContrato.View)

    : LoginContrato.Presenter {

    init {
        fragment.presenter = this
    }
    override fun autentica(login: Usuario?) {
        Log.e("erroLogin ", login.toString())
        pegaUsuario(login)
    }

    private fun pegaUsuario(usuario: Usuario?) {
        fragment.configuraUrlRetrofit()
        usuario?.let {
           // val concentrador =  usuario.converteParaHashMap().criaConcentrador(it)
          //  Log.e("concentrador",concentrador.toString())
            repositorio.pegaUsuario(usuario , object : CallbackResponse<UsuarioResposta> {
                override fun sucesso(response: UsuarioResposta) {
                    Log.e("sucessooo ", response.userAccount[0].toString())
                        fragment.vaiParaHome()
                }
                override fun erro() {
                    Log.e("erooo  ","errooooo")
                }
            })
        }
    }

    override fun start() {
    }
}
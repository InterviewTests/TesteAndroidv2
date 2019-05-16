package com.example.projetobank.ui.login

import android.util.Log
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.UsuarioRepositorio
import com.example.projetobank.util.ehValido

class LoginPresenter
    (
    var repositorio: UsuarioRepositorio,
    val fragment: LoginContrato.View
)

    : LoginContrato.Presenter {

    init {
        fragment.presenter = this
    }

    override fun autentica(login: Usuario) {
        if (valida(login)) {
            fragment.exibeProgressBar()
            try {
                pegaUsuario(login)
            } catch (e: Exception) {
                fragment.exibe("Erro de conexão!")
                Log.e("erroAutentica ", e.message)
            }
        }
    }

    private fun pegaUsuario(usuario: Usuario?) {
        fragment.configuraUrlRetrofit()
        usuario?.let {
            repositorio.pegaUsuario(usuario, object : CallbackResponse<UsuarioResposta> {
                override fun sucesso(response: UsuarioResposta) {
                    fragment.exibe("deeeu certo!")
                   fragment.escondeProgressBar()
                    fragment.vaiParaHome(response.userAccount)
                }

                override fun erro() {
                    Log.e("erroPegarUsuario ","erro")
                }
            })
        }
    }

    override fun start() {
    }

    private fun valida(login: Usuario): Boolean {
        return login.ehValido { autenticacaoCampo ->
                fragment.informaErroDeValidacao(autenticacaoCampo)
        }
    }
}
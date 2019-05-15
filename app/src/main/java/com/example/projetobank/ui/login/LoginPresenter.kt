package com.example.projetobank.ui.login

import android.util.Log
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.UsuarioRepositorio

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
            //fragment.exibeProgressBar()
            try {
                pegaUsuario(login)
            } catch (e: Exception) {
                fragment.exibe("Erro de conexão!")
            }
        }
    }

    private fun pegaUsuario(usuario: Usuario?) {
        fragment.configuraUrlRetrofit()
        usuario?.let {
            repositorio.pegaUsuario(usuario, object : CallbackResponse<userAccount> {
                override fun sucesso(response: userAccount) {
                    Log.e("sucessooo ", response.name)
                  //  fragment.vaiParaHome(response.userAccount[0])
                }

                override fun erro() {
                    Log.e("erooo  ", "errooooo")
                }
            })
        }
    }

    override fun start() {
    }

    private fun valida(login: Usuario): Boolean {
        Log.e("usuariooooo ", login!!.user)
//        return login.ehValido { autenticacaoCampo ->
//                fragment.informaErroDeValidacao(autenticacaoCampo)
//        }
        return true
    }
}
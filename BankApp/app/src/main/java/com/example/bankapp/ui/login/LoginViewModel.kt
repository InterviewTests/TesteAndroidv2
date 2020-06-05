package com.example.bankapp.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entidades.ContaUsuario
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.executores.RealizarLoginExecutor

class LoginViewModel(private val loginExecutor: RealizarLoginExecutor, val app: Application) :
    ViewModel() {
    val usuario = MutableLiveData<String?>()
    val senha = MutableLiveData<String?>()
    val formularioValido = MutableLiveData<Boolean>().apply { value = false }
    val loginResposta: MutableLiveData<LoginResposta?> by lazy {
        MutableLiveData<LoginResposta?>()
    }

    suspend fun realizarLogin(usuario: String, senha: String) {
        val params =
            RealizarLoginExecutor.Parametros(LoginRequisicao(usuario = usuario, senha = senha))
        val resposta = loginExecutor.executar(params)

        loginResposta.postValue(resposta)
    }

    fun formularioValido() {
        formularioValido.postValue(usuarioValido() && senhaValida())
    }

    fun usuarioValido(): Boolean {
        return when (usuario.value.isNullOrBlank()) {
            true -> false
            false -> usuario.value!!.contains("@") && usuario.value!!.contains(".com")
        }
    }

    fun senhaValida(): Boolean {
        return when (senha.value.isNullOrBlank()) {
            true -> false
            false -> senha.value!!.isNotEmpty()
        }
    }
}
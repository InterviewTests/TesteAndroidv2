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
    val loginResposta: MutableLiveData<LoginResposta?> by lazy {
        MutableLiveData<LoginResposta?>()
    }

    suspend fun realizarLogin(usuario: String, senha: String) {
        val params =
            RealizarLoginExecutor.Parametros(LoginRequisicao(usuario = usuario, senha = senha))
        val resposta = loginExecutor.executar(params)

        loginResposta.postValue(resposta)
    }


    fun usuarioValido(valor : String?): Boolean {
        return when(valor.isNullOrBlank()){
            false -> {
                when (valor.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    true -> valor.length == 11
                    false -> valor!!.contains("@") && valor.contains(".com")
                }
            }

            true -> false
        }
    }

     fun senhaValida(valor: String?): Boolean {
         if(valor.isNullOrBlank()) return false

        for (character in valor!!) {
            if (character.isUpperCase()) return true
        }
        return false
    }
}
package com.example.bankapp.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.LoginRequisicao
import com.example.domain.entities.LoginResposta
import com.example.domain.usecases.PerformLoginUseCase

class LoginViewModel(private val loginUseCase: PerformLoginUseCase, val app: Application) :
    ViewModel() {
    val usuario = MutableLiveData<String?>()
    val senha = MutableLiveData<String?>()
    val loginResposta: MutableLiveData<LoginResposta?> by lazy {
        MutableLiveData<LoginResposta?>()
    }

    suspend fun realizarLogin(usuario: String, senha: String) {
        val params =
            PerformLoginUseCase.Parametros(LoginRequisicao(usuario = usuario, senha = senha))
        val resposta = loginUseCase.execute(params)

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
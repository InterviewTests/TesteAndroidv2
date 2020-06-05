package com.example.bankapp.ui.statements

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entidades.ContaUsuario
import com.example.domain.entidades.ListaStatements
import com.example.domain.executores.ListarStatementsExecutor

class MainViewModel(
    private val listarStatementsExecutor: ListarStatementsExecutor,
    val app: Application
) : ViewModel() {
    val contaUsuario = MutableLiveData<ContaUsuario?>()
    val listaStatements: MutableLiveData<ListaStatements?> by lazy {
        MutableLiveData<ListaStatements?>()
    }

    suspend fun listarStatements(idUsuario: Int?) {
        val params = ListarStatementsExecutor.Parametros(id = idUsuario!!)
        val resposta = listarStatementsExecutor.executar(params)

        listaStatements.postValue(resposta)
    }
}
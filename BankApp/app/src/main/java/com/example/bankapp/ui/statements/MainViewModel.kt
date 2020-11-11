package com.example.bankapp.ui.statements

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.ContaUsuario
import com.example.domain.entities.ListaStatements
import com.example.domain.usecases.ListStatementsUseCase

class MainViewModel(
    private val listStatementsUseCase: ListStatementsUseCase,
    val app: Application
) : ViewModel() {
    val contaUsuario = MutableLiveData<ContaUsuario?>()
    val listaStatements: MutableLiveData<ListaStatements?> by lazy {
        MutableLiveData<ListaStatements?>()
    }

    suspend fun listarStatements(idUsuario: Int?) {
        val params = ListStatementsUseCase.Parametros(id = idUsuario!!)
        val resposta = listStatementsUseCase.execute(params)

        listaStatements.postValue(resposta)
    }
}
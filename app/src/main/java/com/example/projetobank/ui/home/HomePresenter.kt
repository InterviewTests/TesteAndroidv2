package com.example.projetobank.ui.home

import com.example.projetobank.data.model.DadosBancarioResposta
import com.example.projetobank.data.model.UsuarioResposta
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.data.source.CallbackResponse
import com.example.projetobank.data.source.DadosBancarioDataSource
import com.example.projetobank.data.source.DadosBancarioRepositorio
import com.example.projetobank.data.source.UsuarioDataSource

class HomePresenter
    (
    val repositorio: DadosBancarioRepositorio,
    val fragment: HomeContrato.View
)

    : HomeContrato.Presenter {

    init {
        fragment.presenter = this
    }

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        listarStatement()
    }

    private fun listarStatement() {
        repositorio.pegaDadosBancario(1, object : CallbackResponse<DadosBancarioResposta> {
            override fun sucesso(response: DadosBancarioResposta) {
                fragment.listarStatement(response.statement)

            }

            override fun erro() {

            }
        })
    }
}
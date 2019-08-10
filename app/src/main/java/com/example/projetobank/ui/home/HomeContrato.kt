package com.example.projetobank.ui.home

import com.example.projetobank.data.model.Statement
import com.example.projetobank.util.BasePresenter
import com.example.projetobank.util.BaseView

class HomeContrato {

    interface View: BaseView<Presenter> {
        fun exibeInformacao(mensagem: String)
        fun listarStatement(statement: List<Statement>)
    }
    interface Presenter: BasePresenter{
        fun logout()
    }
}
package com.example.projetobank.ui.login

import com.example.projetobank.data.model.Usuario
import com.example.projetobank.data.model.userAccount
import com.example.projetobank.util.BasePresenter
import com.example.projetobank.util.BaseView

interface LoginContrato {

    interface View: BaseView<Presenter> {
        fun vaiParaHome(dados : userAccount)
        fun informaErroDeValidacao(loginCampo: LoginCampo ?)
        fun exibe(mensagem: String)
        fun exibeProgressBar()
        fun escondeProgressBar()
    }

    interface Presenter: BasePresenter {
        fun autentica(login: Usuario ?)
    }
}
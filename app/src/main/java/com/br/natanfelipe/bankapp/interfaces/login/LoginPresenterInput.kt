package com.br.natanfelipe.bankapp.interfaces.home

import com.br.natanfelipe.bankapp.view.login.LoginResponse

interface LoginPresenterInput {

    fun presentLoginMetaData(response : LoginResponse)

}
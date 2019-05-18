package br.com.teste.santander.login.interactor

import br.com.teste.santander.login.presenter.LoginPresenter
import br.com.teste.santander.login.repository.LoginRepository
import br.com.teste.santander.utils.ValidationUtils

class LoginInteractorImpl: LoginInteractor {
    var presenter: LoginPresenter? = null
    var repository: LoginRepository? = null

    override fun doLogin(user: String, password: String) {
        if (ValidationUtils.isValidUserName(user) && ValidationUtils.isValidPassword(password)) {
            repository?.doLogin(user, password)
        } else {
            presenter?.showMessage("Login ou senha incorretos!")
        }
    }

}
package pt.felipegouveia.bankapp.domain

import pt.felipegouveia.bankapp.data.login.model.LoginBody

interface LoginRepository {
    fun login(loginBody: LoginBody)
}

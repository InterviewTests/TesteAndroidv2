package pt.felipegouveia.bankapp.domain

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.domain.model.login.Login

interface LoginRepository {
    fun login(loginBody: LoginBody): Single<Login>
}

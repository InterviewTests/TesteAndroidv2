package pt.felipegouveia.bankapp.domain

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginResponse

interface LoginRepository {
    fun login(loginBody: LoginBody): Single<LoginResponse>
}

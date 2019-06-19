package br.com.vinicius.bankapp.domain

import br.com.vinicius.bankapp.infra.BaseCallback

class UserContract {

    interface IUser{
        val email: String
        val password: String

        fun isValid(): Boolean
    }

    interface IRepository {
        fun login(username: String, password: String, onResult: BaseCallback<User>)
    }
}
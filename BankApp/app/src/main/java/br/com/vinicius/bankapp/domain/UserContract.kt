package br.com.vinicius.bankapp.domain

import br.com.vinicius.bankapp.internal.BaseCallback

class UserContract {

    interface IUser{
        val username: String
        val password: String

        fun isValid(): Boolean

        fun startLogin(listener: BaseCallback<User>)
    }

    interface IRepository {
        fun startLogin(username: String, password: String, onResult: BaseCallback<User>)
    }
}
package br.com.vinicius.bankapp.domain.user

import br.com.vinicius.bankapp.domain.user.User
import br.com.vinicius.bankapp.internal.BaseCallback

class UserContract {

    interface IUser{
        val username: String
        val password: String

        fun isValidEmpty(): Boolean

        fun validationPassword():Boolean

        fun validationCpf():Boolean

        fun validationEmail():Boolean

        fun startLogin(listener: BaseCallback<User>)
    }

    interface IRepository {
        fun startLogin(username: String, password: String, onResult: BaseCallback<User>)
    }
}
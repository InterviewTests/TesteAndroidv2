package br.com.vinicius.bankapp.domain

import android.text.TextUtils
import android.util.Patterns
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.ValidationException
import java.io.Serializable

class User(override var username: String, override var password: String) : UserContract.IUser, Serializable{

    var repository: UserContract.IRepository? = null

    var agency: String? = null
    var balance: Double? = null
    var bankAccount: String? = null
    var name: String? = null
    var userId: Int? = null

    constructor(
        username: String,
        password: String,
        agency: String,
        balance: Double,
        bankAccount: String,
        name: String,
        userId: Int
    ):this(username, password) {
        this.agency = agency
        this.balance = balance
        this.bankAccount = bankAccount
        this.name = name
        this.userId = userId
    }

    override fun isValid(): Boolean = (!TextUtils.isEmpty(username)
            && Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length > 6)

    override fun startLogin(listener: BaseCallback<User>) {

        if(repository == null) throw ValidationException("Repository nullable")

        if(username.isEmpty()) throw ValidationException("Username nullable")

        if(password.isEmpty()) throw ValidationException("Password nullable")

        repository?.startLogin(username, password, object : BaseCallback<User>{
            override fun onSuccessful(value: User) {
               listener.onSuccessful(value)
            }

            override fun onUnsuccessful(error: String) {
                listener.onUnsuccessful(error)
            }

        })
    }

}
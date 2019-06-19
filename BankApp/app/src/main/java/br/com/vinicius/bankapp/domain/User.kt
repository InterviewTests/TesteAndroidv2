package br.com.vinicius.bankapp.domain

import android.text.TextUtils
import android.util.Patterns

class User(override var username: String, override var password: String) : UserContract.IUser{

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

}
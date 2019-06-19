package br.com.vinicius.bankapp.domain

import android.text.TextUtils
import android.util.Patterns

class User(override val email: String, override val password: String) : UserContract.IUser{

    override fun isValid(): Boolean = (!TextUtils.isEmpty(email)
            && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6)

}
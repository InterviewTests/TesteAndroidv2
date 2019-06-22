package br.com.vinicius.bankapp.domain

import android.text.TextUtils
import android.util.Patterns
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.CPF_PATTERN
import br.com.vinicius.bankapp.internal.PASSWORD_PATTERN
import br.com.vinicius.bankapp.internal.ValidationException
import java.io.Serializable
import java.util.regex.Pattern

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

    override fun isValidEmpty(): Boolean = (username.isEmpty() || password.isEmpty())

    override fun validationPassword():Boolean{
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    override fun validationCpf():Boolean {
        val pattern = Pattern.compile(CPF_PATTERN)
        val matcher = pattern.matcher(replaceChars(username))
        return matcher.matches()
    }

    private fun replaceChars(cpfFull : String) : String {
        return cpfFull.replace(".", "").replace("-", "")
            .replace("(", "").replace(")", "")
            .replace("/", "").replace(" ", "")
            .replace("*", "")
    }

    override fun validationEmail():Boolean =
        Patterns.EMAIL_ADDRESS.matcher(username).matches()

    override fun startLogin(listener: BaseCallback<User>) {

        if(repository == null) throw ValidationException("Repository nullable")

        if(isValidEmpty()) throw ValidationException("User or Password is empty")

        val v = validationCpf()
        val e = validationEmail()
        if(!(validationCpf() || validationEmail()))
           throw ValidationException("User field must be a email or CPF format")

        if(!validationPassword()) throw ValidationException("Password format is incorrect")

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
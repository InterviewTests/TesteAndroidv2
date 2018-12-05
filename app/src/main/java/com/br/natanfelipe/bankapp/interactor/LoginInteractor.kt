package com.br.natanfelipe.bankapp.interactor

import com.br.natanfelipe.bankapp.interfaces.home.LoginPresenterInput
import com.br.natanfelipe.bankapp.view.login.LoginRequest
import com.br.natanfelipe.bankapp.view.login.LoginResponse
import com.br.natanfelipe.bankapp.interfaces.login.LoginInteractorInput
import com.br.natanfelipe.bankapp.utils.CPFUtil
import com.br.natanfelipe.bankapp.utils.EmailValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.Util
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginInteractor : LoginInteractorInput {

    lateinit var output: LoginPresenterInput


    override fun fetchLoginMetaData(loginRequest: LoginRequest, username: String, pwd: String) {
        var loginResponse = LoginResponse()
        if (loginRequest.api != null) {
            loginRequest.api.doLogin(username, pwd)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({ response ->
                        if (response.userAccount != null) {
                            loginResponse.userAccount.userId = response.userAccount.userId
                            loginResponse.userAccount.bankAccount = response.userAccount.bankAccount
                            loginResponse.userAccount.name = response.userAccount.name
                            loginResponse.userAccount.agency = response.userAccount.agency
                            loginResponse.userAccount.balance = response.userAccount.balance
                        }
                    }, {
                    }, {
                        output.presentLoginMetaData(loginResponse)
                    })
        }    }


    override fun validateFieldsData(username: String, pwd: String): Boolean {
        if (username.isEmpty() && pwd.isEmpty()) {
            return false
        } else if(!validateUsernameData(username) || !validatePwdData(pwd)){
            return false
        }

        return true
    }

    override fun validateUsernameData(username: String): Boolean {
        var emailValidator = EmailValidator()
        var cpfValidator = CPFUtil.myValidateCPF(username)

        if (!emailValidator.isEmailValid(username) && !cpfValidator) {
            return false

        }
        return true
    }

    override fun validatePwdData(pwd: String): Boolean {

        var pattern: Pattern
        var matcher: Matcher

        val PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{0,}\$"

        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(pwd)

        return matcher.matches()

    }
}



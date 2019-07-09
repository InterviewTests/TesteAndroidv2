package br.com.learncleanarchitecture.login.domain

import br.com.learncleanarchitecture.login.presentation.*
import java.util.regex.Pattern

interface LoginInteractorInput {
    fun doLogin(
        request: LoginRequest
    )

    fun validateFields(username: String, password: String)
    fun verifyHaveUser(viewModel: LoginViewModel)
}

class LoginInteractor : LoginInteractorInput {
    override fun verifyHaveUser(viewModel: LoginViewModel) {
        viewModel.verifyHaveUser(output)
    }

    override fun validateFields(username: String, password: String) {

        val abecedario = "abcdefghijklmnopqrstuvwxyz"
        val regexSemArrobaEPonto = Pattern.compile("[$&+,:;=\\\\?#|/'<>^*()%!-]")
        val regex = Pattern.compile("[$&+,:;=\\\\?#|@/'<>.^*()%!-]")
        val upperCase = Pattern.compile("[${abecedario.toUpperCase()}-]")
        val lowerCase = Pattern.compile("[$abecedario-]")
        val alfanumerico = Pattern.compile("[1234567890-]")


        if (fieldsAreEmpty(username, password)) {
            output?.showError(EMPTY_TYPE, EMPTY_ERROR)
        } else {
            if (checkFieldUser(regexSemArrobaEPonto, username)
                &&
                checkFieldPassword(regex, alfanumerico, password, upperCase, lowerCase)
            ) {
                doLogin()
            } else {
                checkWhatFieldIsIncorrect(
                    regexSemArrobaEPonto,
                    regex,
                    alfanumerico,
                    username,
                    password,
                    upperCase,
                    lowerCase
                )
            }
        }
    }

    private fun checkWhatFieldIsIncorrect(
        regexSemArrobaEPonto: Pattern,
        regex: Pattern,
        alfanumerico: Pattern,
        username: String,
        password: String,
        upperCase: Pattern,
        lowerCase: Pattern
    ) {
        if (!checkFieldUser(regexSemArrobaEPonto, username)) {
            output?.showError(USER_NAME_TYPE, USER_NAME_ERROR)
        } else if (!checkFieldPassword(regex, alfanumerico, password, upperCase, lowerCase)) {
            output?.showError(PASSWORD_TYPE, PASSWORD_ERROR)
        }
    }

    private fun doLogin() {
        output?.callLogin()
    }

    private fun fieldsAreEmpty(username: String, password: String) = username.isEmpty() || password.isEmpty()

    private fun checkFieldPassword(
        regex: Pattern,
        alfanumerico: Pattern,
        password: String,
        upperCase: Pattern,
        lowCase: Pattern
    ): Boolean {
        return ((regex.matcher(password).find() &&
                upperCase.matcher(password).find() &&
                alfanumerico.matcher(password).find()) &&
                lowCase.matcher(password).find())
    }

    private fun checkFieldUser(regex: Pattern, username: String): Boolean {
        return (!(regex.matcher(username).find()) &&
                (username.contains("@") || username.contains(".")))
    }

    lateinit var viewModel: LoginViewModel
    var output: LoginPresenterInput? = null

    override fun doLogin(
        request: LoginRequest
    ) {
        viewModel.doLogin(request, output)
    }

    companion object {
        const val TAG = "LoginInteractor"
        const val USER_NAME_TYPE = "USER_NAME_TYPE"
        const val EMPTY_TYPE = "EMPTY_TYPE"
        const val PASSWORD_TYPE = "PASSWORD_TYPE"
        const val USER_NAME_ERROR = "No campo user nao eh permitido caracteres especiais"
        const val PASSWORD_ERROR =
            "No campo password tem que ter pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico. Exemplo: Teste@1"
        const val EMPTY_ERROR = "Preencha os campos user e password"
    }
}
package pt.felipegouveia.bankapp

import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.domain.model.Error
import pt.felipegouveia.bankapp.domain.model.Login

object Mocks {
    private const val USER_EMAIL = "felipegouveia3@gmail.com"
    private const val USER_CPF = "002.169.232-71"
    private const val EMPTY_USER = ""
    private const val BAD_USER = "42THENUMBER"
    private const val PASS = "Test@1"
    private const val BAD_PASS = "aaaaa"
    private const val EMPTY_PASS = ""

    val goodLoginBodyEmail = LoginBody(
        user = USER_EMAIL,
        password = PASS
    )

    val goodLoginBodyCpf = LoginBody(
        user = USER_CPF,
        password = PASS
    )

    val badLoginBodyEmptyUser = LoginBody(
        user = EMPTY_USER,
        password = PASS
    )

    val badLoginBodyEmptyPassword = LoginBody(
        user = USER_EMAIL,
        password = EMPTY_PASS
    )

    val badUserCredentialBody = LoginBody(
        user = BAD_USER,
        password = PASS
    )

    val badPasswordCredentialBody = LoginBody(
        user = USER_EMAIL,
        password = BAD_PASS
    )

    val badUserCredentialResponse = Login(
        userAccount = null,
        error = Error("User in bad format", R.string.app_name)
    )

    val badPasswordCredentialResponse = Login(
        userAccount = null,
        error = Error("Password in bad format", R.string.app_name)
    )

}
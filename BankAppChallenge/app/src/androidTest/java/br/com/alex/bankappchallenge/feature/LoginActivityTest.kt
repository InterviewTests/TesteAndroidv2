package br.com.alex.bankappchallenge.feature

import br.com.alex.bankappchallenge.feature.login.LoginActivity
import br.com.alex.bankappchallenge.rules.InstrumentedTestRule
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val instrumentedTestRule = InstrumentedTestRule(
        LoginActivity::class.java,
        beforeStatement = {
            LoginRobot().mockUserSave("user@gmail.com")
        }
    )

    @Test
    fun shouldShowUserFieldFilled_whenHasUserSaved() {
        loginActivityTest {
            checkUserFieldHasValue("user@gmail.com")
        }
    }

    @Test
    fun shouldShowErrorTextOnUserEditText_whenFieldIsEmpty() {
        loginActivityTest {
            clearUserField()
            clickLoginButton()
            checkErrorMessageOnUserField("Campo User obrigatorio.")
        }
    }

    @Test
    fun shouldShowErrorTextOnPasswordEditText_whenFieldIsEmpty() {
        loginActivityTest {
            clickLoginButton()
            checkErrorMessageOnPassowordField("Campo Password obrigatorio.")
        }
    }

    @Test
    fun shouldShowPasswordError_whenPasswordNotContainsCapitalLetterNumberAndEspecialCharacters() {
        loginActivityTest {
            typeTextOnPasswordField("password")
            clickLoginButton()
            checkErrorMessageOnPassowordField("Senha deve conter pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico.")
        }
    }

    @Test
    fun shouldShowUserError_whenUserIsNotEmailOrCPF() {
        loginActivityTest {
            clearUserField()
            typeTextOnUserField("user")
            typeTextOnPasswordField("password")
            clickLoginButton()
            checkErrorMessageOnUserField("Campo User deve ser email ou cpf")
        }
    }
}
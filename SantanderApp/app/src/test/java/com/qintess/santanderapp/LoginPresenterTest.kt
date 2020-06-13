package com.qintess.santanderapp

import android.os.Build
import androidx.test.core.app.ActivityScenario.launch
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.ui.view.loginScreen.LoginActivity
import com.qintess.santanderapp.ui.view.loginScreen.LoginActivityInput
import com.qintess.santanderapp.ui.view.loginScreen.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class LoginPresenterTest {
    // Ao chamar presentLastUser deve-se chamar displayLastUser
    @Test
    fun onPresentLastUser_shouldCallDisplayLastUser(){
        val presenter = LoginPresenter()
        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        presenter.output = WeakReference(loginPresenterOutputSpy)
        presenter.presentLastUser("raphacmartin")

        Assert.assertTrue(loginPresenterOutputSpy.displayLastUserIsCalled)
    }

    // Ao chamar presentLastUser deve-se exibir o usuário correto no campo
    @Test
    fun onPresentLastUser_shouldSetCorrectTextInField() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val presenter = LoginPresenter()
            presenter.output = WeakReference(activity)
            val username = "raphacmartin"
            presenter.presentLastUser(username)

            Assert.assertEquals(username, activity.usernameField.text.toString())
        }
    }

    // Ao chamar presentLoginErrorMessage deve-se chamar showAlert
    @Test
    fun onPresentLoginErrorMessage_shouldCallShowAlert() {
        val presenter = LoginPresenter()
        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        presenter.output = WeakReference(loginPresenterOutputSpy)
        presenter.presentErrorMessage(Validator.CREDENTIALS_TITLE_ERROR, Validator.USER_ERROR)

        Assert.assertTrue(loginPresenterOutputSpy.showAlertIsCalled)
    }


    class LoginPresenterOutputSpy: LoginActivityInput {
        var displayLastUserIsCalled = false
        var showAlertIsCalled = false
        override fun createListeners() { return }

        override fun checkLastUser() { return }

        override fun displayLastUser(username: String) {
            displayLastUserIsCalled = true
            return
        }

        override fun login() { return }

        override fun showAlert(title: String, msg: String): Boolean {
            showAlertIsCalled = true
            return false
        }

    }
}
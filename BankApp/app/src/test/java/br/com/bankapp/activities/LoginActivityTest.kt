package br.com.bankapp.activities

import android.os.Build
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.R
import br.com.bankapp.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class LoginActivityTest {

    private lateinit var loginActivity: LoginActivity

    @Before
    fun setup() {
        loginActivity =
            Robolectric.buildActivity(LoginActivity::class.java)
                .create()
                .resume()
                .get()
    }

    @Test
    fun `validate components are being displayed`() {
        val loginText =
            loginActivity.findViewById<TextView>(R.id.login_text)
        val inputUserLayout =
            loginActivity.findViewById<TextInputLayout>(R.id.input_user_layout)
        val inputPasswordLayout =
            loginActivity.findViewById<TextInputLayout>(R.id.input_password_layout)
        val loginProgressBar =
            loginActivity.findViewById<ProgressBar>(R.id.login_progress_bar)
        val loginButton =
            loginActivity.findViewById<Button>(R.id.login_button)

        assertThat(loginText, notNullValue())
        assertThat(loginText.isVisible, `is`(true))

        assertThat(inputUserLayout, notNullValue())
        assertThat(inputUserLayout.isVisible, `is`(true))

        assertThat(inputPasswordLayout, notNullValue())
        assertThat(inputPasswordLayout.isVisible, `is`(true))

        assertThat(loginProgressBar, notNullValue())
        assertThat(loginProgressBar.isVisible, `is`(false))

        assertThat(loginButton, notNullValue())
        assertThat(loginButton.isVisible, `is`(true))
    }
}
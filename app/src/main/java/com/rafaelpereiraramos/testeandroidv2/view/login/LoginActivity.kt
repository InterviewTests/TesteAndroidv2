package com.rafaelpereiraramos.testeandroidv2.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rafaelpereiraramos.testeandroidv2.R
import com.rafaelpereiraramos.testeandroidv2.core.ViewModelFactory
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.view.login.LoginActivityViewModel.Status.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginActivityViewModel::class.java)

        setEvents()
        subscribe()
    }

    private fun setEvents() {
        action_login.setOnClickListener {
            if (validate()) {
                viewModel.login(input_user_name.text.toString(), input_password.text.toString())
            }
        }
    }

    private fun subscribe() {
        viewModel.status.observe(this, Observer { status ->

            when(status!!) {
                LOGGED -> openStatement(viewModel.user)
                CREDENTIALS_NOT_FOUND -> {}
            }
        })
    }

    private fun validate(): Boolean {
        var isValid = true
        var input: String?

        input = input_user_name.text.toString()
        containerUserName.error = if (input.isNullOrEmpty()) {
            isValid = false
            getString(R.string.error_empty_field)
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            if (!isValidCpf(input)) {
                isValid = false
                getString(R.string.error_login_format)
            } else null
        } else null


        input = input_password.text.toString()
        containerPassword.error = if (input.isNullOrEmpty()) {
            isValid = false
            getString(R.string.error_empty_field)
        } else if (!isValidPassword(input)) {
            isValid = false
            getString(R.string.error_pass_format)
        } else null

        return isValid
    }

    private fun isValidCpf(cpf: String): Boolean {
        return Pattern.matches(
            """\d{3}\.\d{3}\.\d{3}-\d{2}""",
            cpf
        )
    }

    private fun isValidPassword(pass: String): Boolean {
        return Pattern.matches(
            """^.*(?=.*\d+)(?=.*[A-Z]+)(?=.*[\W]).*$""",
            pass
        )
    }

    private fun openStatement(userTO: UserTO) {

    }
}

package com.rafaelpereiraramos.testeandroidv2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelpereiraramos.testeandroidv2.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setEvents()
    }

    private fun setEvents() {
        action_login.setOnClickListener {
            if (validate()) null
        }
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
            getString(R.string.error_empty_field)
        } else null

        return isValid
    }

    private fun isValidCpf(cpf: String): Boolean {
        return Pattern.matches(
            """\d{3}\.\d{3}\.\d{3}-\d{2}""",
            cpf
        )
    }
}

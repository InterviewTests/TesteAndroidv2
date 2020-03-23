package com.example.ibm_test.clean_code.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.ibm_test.R
import com.example.ibm_test.utils.hasOneUpperCase
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginActivityInput, TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            onClickLogin()
        }

        input_edit_user.addTextChangedListener(this)
        input_edit_password.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        input_layout_user.isErrorEnabled = false
        input_layout_password.isErrorEnabled = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


    private fun onClickLogin() {
        val getTextUser = input_edit_user?.text
        val getTextPassword = input_edit_password?.text

        when {
            getTextUser?.trim().isNullOrEmpty() -> {
                input_layout_user.isErrorEnabled = true
                input_layout_user.error = getString(R.string.alert_to_field_empty)
            }
            getTextPassword?.trim().isNullOrEmpty() -> {
                input_layout_password.isErrorEnabled = true
                input_layout_password.error = getString(R.string.alert_to_field_empty)
            }
            getTextPassword.toString().hasOneUpperCase() -> {
                input_layout_password.isErrorEnabled = true
                input_layout_password.error = getString(R.string.missing_upper_case)
            }
        }
    }

    fun setupErrorToFieldPassword(text: String){
        input_layout_password.isErrorEnabled = true
        input_layout_password.error = text
    }

    fun setupErrorToFieldUser(text: String){
        input_layout_user.isErrorEnabled = true
        input_layout_user.error = text
    }
}

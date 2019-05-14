package com.example.testeandroidv2.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testeandroidv2.R
import com.example.testeandroidv2.RetrofitInitializer
import com.example.testeandroidv2.Utils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var mUser: String
    private lateinit var mPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListeners()
    }

    private fun setListeners() {
        login_button.setOnClickListener {
            checkLoginData()
        }
    }

    private fun checkLoginData() {
        mUser = user_input.text.toString()
        mPassword = password_input.text.toString()

        val isLoginValid = Utils().checkLoginData(mUser, mPassword)

        if (isLoginValid) {
            requestLogin()
        } else {
            showAlertDialog()

            user_input.requestFocus()
        }
    }

    private fun requestLogin() {
        LoginBody(mUser, mPassword)
    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(this@LoginActivity)
            .setTitle(R.string.error_title)
            .setMessage(R.string.login_error_message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }
}
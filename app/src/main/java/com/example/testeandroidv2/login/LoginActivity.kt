package com.example.testeandroidv2.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testeandroidv2.ProgressDialogActivity
import com.example.testeandroidv2.R
import com.example.testeandroidv2.RetrofitInitializer
import com.example.testeandroidv2.Utils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response


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
            showAlertDialog(getString(R.string.login_data_error_message))

            user_input.requestFocus()
        }
    }

    private fun requestLogin() {
        val callLogin = RetrofitInitializer().requestLogin().login(LoginBody(mUser, mPassword))

        startActivity(Intent(this@LoginActivity, ProgressDialogActivity::class.java))

        callLogin?.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                showAlertDialog(getString(R.string.login_error_message))
            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

            }
        })
    }

    private fun showAlertDialog(mMessage: String) {
        MaterialAlertDialogBuilder(this@LoginActivity)
            .setTitle(R.string.error_title)
            .setMessage(mMessage)
            .setPositiveButton(R.string.ok, null)
            .show()
    }
}
package com.example.testeandroidv2.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testeandroidv2.model.login.LoginBody
import com.example.testeandroidv2.model.login.LoginResponse
import com.example.testeandroidv2.R
import com.example.testeandroidv2.service.LoginService
import com.example.testeandroidv2.service.RetrofitInitializer
import com.example.testeandroidv2.util.Utils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        mUser = user_input_field.text.toString()
        mPassword = password_input_field.text.toString()

        val isLoginValid = Utils().checkLoginData(mUser, mPassword)

        if (isLoginValid) {
            requestLogin()
        } else {
            showAlertDialog(getString(R.string.login_data_error_message))

            user_input_layout.requestFocus()
        }
    }

    private fun requestLogin() {
        val loginService = RetrofitInitializer().createLoginService()
        val loginBody = LoginBody(mUser, mPassword)
        val callLogin = loginService.login(loginBody)

        callLogin?.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                showAlertDialog(getString(R.string.login_error_message))
            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if (!response?.isSuccessful!!) {
                    showAlertDialog(getString(R.string.login_error_message))
                } else {
                    val statementActivity = Intent(this@LoginActivity, StatementActivity::class.java)

                    response.body()?.let { loginResponse ->
                        statementActivity
                            .putExtra(getString(R.string.userId), loginResponse.userAccount.userId)
                            .putExtra(getString(R.string.name), loginResponse.userAccount.name)
                            .putExtra(getString(R.string.bankAccount), loginResponse.userAccount.bankAccount)
                            .putExtra(getString(R.string.agency), loginResponse.userAccount.agency)
                            .putExtra(getString(R.string.balance), loginResponse.userAccount.balance)
                    }

                    startActivity(statementActivity)
                }
            }
        })

        startActivity(Intent(this@LoginActivity, ProgressDialogActivity::class.java))
    }

    private fun showAlertDialog(mMessage: String) {
        MaterialAlertDialogBuilder(this@LoginActivity)
            .setTitle(R.string.error_title)
            .setMessage(mMessage)
            .setPositiveButton(R.string.ok, null)
            .show()
    }
}
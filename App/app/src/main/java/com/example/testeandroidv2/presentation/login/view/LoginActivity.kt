package com.example.testeandroidv2.presentation.login.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.login.LoginApiDataSource
import com.example.testeandroidv2.domain.login.User
import com.example.testeandroidv2.presentation.statements.view.StatementsActivity
import com.example.testeandroidv2.utilHelper.Constants
import com.example.testeandroidv2.presentation.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userLogged = getUserLogged()
        edit_text_user.setText(userLogged)

        val viewModel: LoginViewModel = LoginViewModel.ViewModelFactory(LoginApiDataSource())
            .create(LoginViewModel::class.java)

        viewModel.viewFlipperLoginLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                view_flipper_login.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessage ->
                    text_error_login.text = getString(errorMessage)
                }
            }
        })

        button_login.setOnClickListener { buttonLogin ->

            buttonLogin.isEnabled = false

            var userText = edit_text_user.text.toString()
            val pswText = edit_text_password.text.toString()

            val user = User(
                userText,
                pswText
            )

            viewModel.login(user)
            button_login.text = getString(R.string.loading)
            viewModel.loginLiveData.observe(this, Observer {

                it?.let { loginResponse ->

                    setUserLogged(userText)

                    val intent = Intent(this, StatementsActivity::class.java)
                    intent.putExtra("name", loginResponse.name)
                    intent.putExtra("bankAccount", loginResponse.bankAccount)
                    intent.putExtra("agency", loginResponse.agency)
                    intent.putExtra("balance", loginResponse.balance)

                    finish()
                    startActivity(intent)
                }
            })
        }
    }

    override fun onBackPressed() {
        if (view_flipper_login.displayedChild == 1) {
            view_flipper_login.displayedChild = 0
            button_login.isEnabled = true
            button_login.text = getString(R.string.login_button)
        }
        else {
            finish()
        }
    }

    private fun getUserLogged(): String? {
        val sharedPref: SharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        return sharedPref.getString(Constants.PREF_NAME, null)
    }

    private fun setUserLogged(user: String) {
        val sharedPref: SharedPreferences = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(Constants.PREF_NAME, user)
        editor.apply()
    }
}

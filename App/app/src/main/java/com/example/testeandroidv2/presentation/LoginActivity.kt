package com.example.testeandroidv2.presentation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testeandroidv2.R
import com.example.testeandroidv2.model.User
import com.example.testeandroidv2.util.checkPassword
import com.example.testeandroidv2.util.checkUser
import com.example.testeandroidv2.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item.view.*

class LoginActivity : AppCompatActivity() {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "user-logged"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel: LoginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val userLogged = getUserLogged()
        edit_text_user.setText(userLogged)

        button_login.setOnClickListener { buttonLogin ->

            buttonLogin.isEnabled = false

            var userText = edit_text_user.text.toString()
            val pswText = edit_text_password.text.toString()

            val user = User(userText, pswText)

            if (checkPassword(user.password) && checkUser(user.user)) {

                viewModel.login(user)
                viewModel.loginValue.observe(this, Observer {

                    it?.let { loginResponse ->

                        setUserLogged(userText)

                        val intent = Intent(this, StatementsActivity::class.java)
                        intent.putExtra("name", loginResponse.userAccount.name)
                        intent.putExtra("bankAccount", loginResponse.userAccount.bankAccount)
                        intent.putExtra("agency", loginResponse.userAccount.agency)
                        intent.putExtra("balance", loginResponse.userAccount.balance)

                        finish()
                        startActivity(intent)
                    }
                })
            }
            else {
                buttonLogin.isEnabled = true
                Toast.makeText(this@LoginActivity, "Erro no login do usuário ${user.user}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUserLogged(): String? {
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPref.getString(PREF_NAME, null)
    }

    private fun setUserLogged(user: String) {
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(PREF_NAME, user)
        editor.apply()
    }
}

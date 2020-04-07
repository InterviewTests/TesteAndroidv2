package com.example.testeandroidv2.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.testeandroidv2.R
import com.example.testeandroidv2.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel: LoginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        button_login.setOnClickListener {
            val intent = Intent(this, StatementsActivity::class.java)
            startActivity(intent)
        }
    }
}

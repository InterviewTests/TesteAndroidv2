package com.jisellemartins.bank.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jisellemartins.bank.R
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.viewmodel.LoginViewModel
import com.jisellemartins.bank.viewmodel.StatementsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val loginViewModel: LoginViewModel by viewModel()
    val statementsViewModel: StatementsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //loginViewModel.login(Login("jiselle", "12345"))
        statementsViewModel.getDetails("1")
    }
}
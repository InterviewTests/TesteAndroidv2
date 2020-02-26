package br.com.bank.android.login.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.BaseActivity
import br.com.bank.android.R
import br.com.bank.android.databinding.ActivityLoginBinding
import br.com.bank.android.login.business.LoginModel
import br.com.bank.android.login.factory.LoginViewModelFactory
import br.com.bank.android.login.handler.LoginHandler
import br.com.bank.android.login.presentation.model.LoginViewModel

class LoginActivity : BaseActivity(), LoginHandler {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        factory = LoginViewModelFactory(LoginModel(), this)
        val loginViewModel =
            ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.model = loginViewModel
    }

    override fun logged() {

    }
}
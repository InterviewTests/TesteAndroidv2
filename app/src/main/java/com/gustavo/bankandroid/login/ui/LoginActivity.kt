package com.gustavo.bankandroid.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.login.injection.LoginInjection
import com.gustavo.bankandroid.login.usecase.AuthenticateUserUseCaseMock
import com.gustavo.bankandroid.login.usecase.LoginUseCases
import com.gustavo.bankandroid.login.usecase.StoreUserInfoUseCaseMock
import com.gustavo.bankandroid.login.viewmodel.LoginViewModel
import com.gustavo.bankandroid.login.viewmodel.LoginViewModelFactory
import com.gustavo.bankandroid.statements.ui.StatementActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.listeners.onClick

class LoginActivity : AppCompatActivity(), LoginInjection {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = getViewModel()
        setListeners()
        serObservers()
    }

    private fun serObservers() {
        viewModel.loginSuccessLiveData.observe(this, Observer {
            if(it){
                startActivity(Intent(this, StatementActivity::class.java))
            }else{
                showError()
            }
        })
    }

    private fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setListeners() {
        loginButton.onClick {
            viewModel.login(userEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun getViewModel() =
        ViewModelProviders.of(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)

    override val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase
        get() = AuthenticateUserUseCaseMock()
    override val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase
        get() = StoreUserInfoUseCaseMock()
}

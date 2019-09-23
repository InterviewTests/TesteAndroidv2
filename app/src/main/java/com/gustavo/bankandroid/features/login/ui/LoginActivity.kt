package com.gustavo.bankandroid.features.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.contants.Constants
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.injection.LoginInjection
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.login.repository.UserRepositoryImpl
import com.gustavo.bankandroid.features.login.usecase.AuthenticateUserUseCaseMock
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases
import com.gustavo.bankandroid.features.login.usecase.StoreUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.login.viewmodel.LoginViewModel
import com.gustavo.bankandroid.features.login.viewmodel.LoginViewModelFactory
import com.gustavo.bankandroid.features.statements.ui.StatementActivity
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
            if (it) {
                startActivity(Intent(this, StatementActivity::class.java))
            } else {
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

    override val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase by lazy {
        AuthenticateUserUseCaseMock()
    }
    override val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase by lazy {
        StoreUserInfoUseCaseImpl(userRepository)
    }
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(database, ServerIterator())
    }

    override val database: UserDatabase by lazy {
        Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            Constants.USER_DATABASE
        ).build()
    }
}

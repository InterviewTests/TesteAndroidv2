package com.earaujo.santander.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.earaujo.santander.R
import com.earaujo.santander.repository.LoginRepositoryImpl
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.UserAccountModel
import com.earaujo.santander.util.Preferences
import com.earaujo.santander.repository.Status.*
import com.earaujo.santander.repository.models.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ref.WeakReference

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var loginInteractorInput: LoginInteractorInput
    lateinit var loginRouter: LoginRouter
    var userAccount: UserAccountModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupHomeActivity()
        loginInteractorInput.checkUserLoggedIn()
    }

    fun setupHomeActivity() {
        loginRouter = LoginRouter()
        loginRouter.activity = WeakReference(this)

        loginInteractorInput = LoginInteractor(LoginRepositoryImpl(), LoginValidator()).also {
            it.loginPresenterInput = LoginPresenter().also {
                it.loginActivityInput = WeakReference(this)
            }
        }

        btn_login.setOnClickListener {
            performLogin()
        }
        et_password.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performLogin()
            }
            false
        }

        Preferences.setSharePreference(this)
    }

    fun performLogin() {
        val userName = et_username.text.toString()
        val password = et_password.text.toString()
        loginInteractorInput.performLogin(LoginRequest(userName, password))
    }

    override fun loginCallback(loginResponse: Resource<LoginResponse>) {
        when (loginResponse.status) {
            LOADING -> {
                pb_loading.visibility = View.VISIBLE
            }
            SUCCESS -> {
                pb_loading.visibility = View.GONE
                Preferences.setUserName(et_username.text.toString())
                this.userAccount = loginResponse.data!!.userAccountModel
                loginRouter.startStatementScreen()
                tv_error_message.visibility = View.INVISIBLE
            }
            ERROR -> {
                pb_loading.visibility = View.GONE
                tv_error_message.visibility = View.VISIBLE
                tv_error_message.text = when(loginResponse.message) {
                    LoginInteractorErros.WRONG_USERNAME.errorNo -> getString(R.string.login_error_message_invalid_username)
                    LoginInteractorErros.WRONG_PASSWORD.errorNo -> getString(R.string.login_error_message_invalid_password)
                    else -> ""
                }
            }
        }
    }

    override fun displayUserName(userName: String) {
        et_username.setText(userName)
        et_password.requestFocus()
    }
}

interface LoginActivityInput {
    fun loginCallback(loginResponse: Resource<LoginResponse>)
    fun displayUserName(userName: String)
}

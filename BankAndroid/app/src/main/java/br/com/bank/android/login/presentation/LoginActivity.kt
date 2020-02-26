package br.com.bank.android.login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.bank.android.BaseActivity
import br.com.bank.android.R
import br.com.bank.android.core.retrofit.auth.BankAuthService
import br.com.bank.android.databinding.ActivityLoginBinding
import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.exceptions.PasswordInvalid
import br.com.bank.android.exceptions.UserInvalid
import br.com.bank.android.home.presentation.HomeActivity
import br.com.bank.android.infra.BankPreferences
import br.com.bank.android.infra.Constants
import br.com.bank.android.login.business.LoginBusiness
import br.com.bank.android.login.business.LoginModel
import br.com.bank.android.login.data.UserAccount
import br.com.bank.android.login.factory.LoginViewModelFactory
import br.com.bank.android.login.handler.LoginHandler
import br.com.bank.android.login.presentation.model.LoginViewModel

class LoginActivity : BaseActivity(), LoginHandler {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: LoginViewModelFactory
    private lateinit var loginBusiness: LoginBusiness
    private lateinit var vieModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        super.onCreate(savedInstanceState)

        loginBusiness = LoginModel(BankAuthService())
        factory = LoginViewModelFactory(loginBusiness, this)

        vieModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = vieModel

        listernerTextChangedListener()
        verifyInitLogin()
    }

    private fun verifyInitLogin() {
        vieModel.user.set(BankPreferences.getPreferenceString(Constants.USER))
        vieModel.password.set(BankPreferences.getPreferenceString(Constants.PASSWORD))
    }

    private fun listernerTextChangedListener() {
        binding.textInputUser.editText?.addTextChangedListener {
            binding.textInputUser.error = null
        }

        binding.textInputPassword.editText?.addTextChangedListener {
            binding.textInputPassword.error = null
        }
    }

    override fun logged(userAccount: UserAccount) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(UserAccount::class.java.simpleName, userAccount)
        startActivity(intent)
        finish()
    }

    override fun setLoading(loading: Boolean) {
        setShowLoading(loading)
    }

    override fun onError(error: BusinessError) {
        if (error is UserInvalid) {
            showError(binding.textInputUser, error.getMessage(this))
            return
        }

        if (error is PasswordInvalid) {
            showError(binding.textInputPassword, error.getMessage(this))
            return
        }

        showErrorDialog(error)
    }
}
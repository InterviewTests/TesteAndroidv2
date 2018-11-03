package com.ygorcesar.testeandroidv2.login.presentation

import com.ygorcesar.testeandroidv2.R
import com.ygorcesar.testeandroidv2.base.common.exception.AppException
import com.ygorcesar.testeandroidv2.base.common.utils.CPFEmailTextWatcher
import com.ygorcesar.testeandroidv2.base.data.ViewState
import com.ygorcesar.testeandroidv2.base.data.getList
import com.ygorcesar.testeandroidv2.base.extensions.*
import com.ygorcesar.testeandroidv2.base.presentation.BaseActivity
import com.ygorcesar.testeandroidv2.login.domain.LoginBusiness
import com.ygorcesar.testeandroidv2.login.viewmodel.LoginViewModel
import com.ygorcesar.testeandroidv2.managers.NavigationManager
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : BaseActivity() {

    override val showAutomaticMessage = true
    override val layoutResId = R.layout.login_activity
    private lateinit var viewModel: LoginViewModel

    override fun onInit() {
        if (isAuthenticated()) NavigationManager.navigateToHome(this)

        viewModel = provideViewModel(viewModelFactory) {
            observe(loginResponseState, ::onLoginResponseState)
            failure(appException, ::onAppExceptionError)
        }

        loginUserEditText.addTextChangedListener(CPFEmailTextWatcher())
        onLoginClick()
    }

    private fun onLoginResponseState(viewState: ViewState?) {
        when (viewState) {
            ViewState.Loading -> loading(true)
            ViewState.Success -> {
                loading(false)
                NavigationManager.navigateToHome(this@LoginActivity)
            }
            is ViewState.Complete<*> -> {
                viewState.getList<String>()
            }
            else -> loading(false)
        }
    }

    private fun onAppExceptionError(appException: AppException?) {
        loading(false)
        checkResponseException(appException) { exception ->
            when (exception) {
                LoginBusiness.UserInvalid -> {
                    loginUserEditText.error =
                            getString(R.string.login_validation_error_user_invalid)
                }
                LoginBusiness.PasswordInvalid -> {
                    loginPasswordEditText.error =
                            getString(R.string.login_validation_error_password_invalid)
                }
                LoginBusiness.PasswordNeedSpecialCharacter -> {
                    loginPasswordEditText.error =
                            getString(R.string.login_validation_error_password_character_special)
                }
                LoginBusiness.PasswordNeedCapitalizedLetter -> {
                    loginPasswordEditText.error =
                            getString(R.string.login_validation_error_password_capitalized_letter)
                }
                LoginBusiness.PasswordNeedNumber -> {
                    loginPasswordEditText.error =
                            getString(R.string.login_validation_error_password_number)
                }
            }
        }
    }

    private fun onLoginClick() {
        loginSignInButton?.setOnClickListener {
            clearErrors()
            val user = loginUserEditText?.text?.toString() ?: ""
            val password = loginPasswordEditText?.text?.toString() ?: ""
            viewModel.login(user, password)
        }
    }

    private fun clearErrors() {
        loginUserEditText?.error = null
        loginPasswordEditText?.error = null
    }

    override fun loading(isLoading: Boolean) {
        super.loading(isLoading)
        if (isLoading) loginProgress.visible() else loginProgress.gone()
        val enabledComponents = !isLoading
        loginUserEditText?.isEnabled = enabledComponents
        loginPasswordEditText?.isEnabled = enabledComponents
        loginSignInButton?.isEnabled = enabledComponents
    }

}
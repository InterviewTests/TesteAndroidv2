package com.jeanjnap.bankapp.ui.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.databinding.ActivityLoginBinding
import com.jeanjnap.bankapp.ui.statements.StatementsActivity
import com.jeanjnap.bankapp.ui.base.BaseActivity
import com.jeanjnap.bankapp.util.extension.changeStatusBarColorToWhite
import com.jeanjnap.bankapp.util.extension.observe

import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        configureElements()
        listenUi()
    }

    private fun configureElements() {
        changeStatusBarColorToWhite()
        binding.tietPass.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.onLoginClick()
            }
            false
        }
    }

    private fun listenUi() {
        observe(viewModel.username, binding.tietUser::setText)
        observe(viewModel.loading, ::onLoading)
        observe(viewModel.usernameError, ::onUsernameError)
        observe(viewModel.passwordError, ::onPasswordError)
        observe(viewModel.loginSuccess) { StatementsActivity.clearTopStart(this, it) }
    }

    private fun onLoading(isLoading: Boolean) {
        with(binding.btLogin) {
            text = getString(
                if (isLoading) R.string.loading
                else R.string.login
            )
            isEnabled = !isLoading
        }
    }

    private fun onUsernameError(isWrong: Boolean) {
        binding.tilUser.error = if (isWrong) getString(R.string.invalid_username) else null
    }

    private fun onPasswordError(isWrong: Boolean) {
        binding.tilPass.error = if (isWrong) getString(R.string.invalid_password) else null
    }
}
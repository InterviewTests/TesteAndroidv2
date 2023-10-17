package fingerfire.com.extractbank.features.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import fingerfire.com.extractbank.R
import fingerfire.com.extractbank.databinding.ActivityLoginBinding
import fingerfire.com.extractbank.features.login.ui.viewstate.LoginViewState
import fingerfire.com.extractbank.features.statements.ui.StatementActivity
import fingerfire.com.extractbank.model.Login
import fingerfire.com.extractbank.model.User
import fingerfire.com.extractbank.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val USER = "USER"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupLoginButton()
        observeLoginState()
        observeSavedUser()
    }

    private fun observeLoginState() {
        viewModel.loginLiveData.observe(this) { loginViewState ->
            when (loginViewState) {
                is LoginViewState.Success -> {
                    navigateToStatements(loginViewState.user)
                }

                is LoginViewState.Error -> {
                    Utils.showError(loginViewState.message, this)
                }
            }
        }
    }

    private fun observeSavedUser() {
        viewModel.getSavedUserLiveData.observe(this) {
            if (!it.isNullOrBlank()) {
                binding.etEmailCpf.setText(it)
            }
        }
    }

    private fun isEmailOrCpfValid(emailOrCpf: String): Boolean {
        return viewModel.isValidEmailOrCPF(emailOrCpf)
    }

    private fun isPasswordValid(password: String): ValidationResult {
        return viewModel.isPasswordValid(password)
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val emailOrCpf = binding.etEmailCpf.text.toString()
            val password = binding.etPassword.text.toString()

            val isEmailOrCpfValid = isEmailOrCpfValid(emailOrCpf)
            val (isPasswordValid, passwordErrorMessage) = isPasswordValid(password)

            if (isEmailOrCpfValid && isPasswordValid) {
                val loginData = Login(emailOrCpf, password)
                viewModel.loginUser(loginData)
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                if (!isEmailOrCpfValid) {
                    Utils.showError(getString(R.string.error_invalid_email_or_cpf), this)
                } else if (!isPasswordValid) {
                    Utils.showError(
                        passwordErrorMessage ?: getString(R.string.error_password_requirements),
                        this
                    )
                }
            }
        }
    }

    private fun navigateToStatements(user: User) {
        val intent = Intent(this, StatementActivity::class.java).apply {
            putExtras(bundleOf(USER to user))
        }
        binding.progressBar.visibility = View.INVISIBLE
        startActivity(intent)
        finish()
    }
}

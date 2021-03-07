package br.com.silas.testeandroidv2.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.silas.testeandroidv2.R
import br.com.silas.testeandroidv2.databinding.ActivityLoginBinding
import br.com.silas.testeandroidv2.ui.statements.StatementsActivity
import br.com.silas.testeandroidv2.util.Validate
import br.com.silas.testeandroidv2.util.Validate.clearErrorText
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        fetchLogin()
        observerSaveUser()
        observerUser()
        observerLoading()
        observerErrorLogin()
        observerUserLogged()
    }

    override fun onResume() {
        super.onResume()
        getLastUserLogged()
    }

    private fun fetchLogin() {
        binding.buttonLogin.setOnClickListener {
            if (!validateLogin(binding.textInputLogin.text.toString())) {
                clearErrorText(binding.textInputLogin, binding.textInputLayoutLogin)
                return@setOnClickListener
            }

            if (!validatePassword(binding.textInputPassword.text.toString())) {
                clearErrorText(binding.textInputPassword, binding.textInputLayoutPassword)
                return@setOnClickListener
            }

            loginViewModel.fetUser(
                binding.textInputLogin.text.toString(),
                binding.textInputPassword.text.toString()
            )
        }
    }


    private fun observerUser() {
        loginViewModel.user.observe(this, {
            loginViewModel.saveUser(it)
        })
    }

    private fun observerSaveUser() {
        loginViewModel.userSaved.observe(this, {
            when (it) {
                true ->
                    loginViewModel.user.value?.let { user -> StatementsActivity.start(this, user) }
                false -> Toast.makeText(
                    this,getString(R.string.msg_error_save_user),
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    private fun observerLoading() {
        loginViewModel.loading.observe(this, {
            when (it) {
                true -> {
                    binding.buttonLogin.visibility = View.GONE
                    binding.progressBarLogin.visibility = View.VISIBLE
                    binding.textViewUserLogged.visibility = View.INVISIBLE
                }
                false -> {
                    binding.buttonLogin.visibility = View.VISIBLE
                    binding.progressBarLogin.visibility = View.GONE
                    binding.textViewUserLogged.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observerErrorLogin() {
        loginViewModel.errorLogin.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })
    }

    private fun getLastUserLogged() {
        loginViewModel.getLastLoggedUser()
    }

    private fun observerUserLogged() {
        loginViewModel.lastUserLogged.observe(this, {
            binding.textViewUserLogged.text = it.name
        })
    }

    private fun validateLogin(login: String): Boolean {
        val isEmail = Validate.validateEmail(login)
        val isCpf = Validate.validateCpf(login)

        if (!isEmail && !isCpf) {
            binding.textInputLayoutLogin.error = getString(R.string.msg_invalid_email_or_cpf)
            return false
        }

        if (isEmail) {
            if (!Validate.validateEmail(login)) {
                binding.textInputLayoutLogin.error = getString(R.string.msg_invalid_email)

                return false
            }
        }

        if (isCpf) {
            if (!Validate.validateCpf(login)) {
                binding.textInputLayoutLogin.error = getString(R.string.msg_invalid_cpf)
                return false
            }
        }

        return true
    }

    private fun validatePassword(password: String): Boolean {
        if (!Validate.validatePassword(password)) {
            binding.textInputLayoutPassword.error = getString(R.string.msg_invalid_password)
            return false
        }

        return true
    }

}

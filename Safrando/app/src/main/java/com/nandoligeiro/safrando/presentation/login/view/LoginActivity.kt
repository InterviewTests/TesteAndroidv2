package com.nandoligeiro.safrando.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nandoligeiro.safrando.databinding.ActivityLoginBinding
import com.nandoligeiro.safrando.presentation.common.Constants
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel
import com.nandoligeiro.safrando.presentation.login.state.LoginState
import com.nandoligeiro.safrando.presentation.login.viewmodel.LoginViewModel
import com.nandoligeiro.safrando.presentation.statements.view.StatementActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeVMEvents()
        observeUser()
        actionSignIn()
        actionGetLocalUser()
    }

    private fun actionGetLocalUser() {
        viewModel.getLocalUserSaved()
    }

    private fun actionSignIn() {
        binding.buttonLogin.setOnClickListener {
            binding.run {
                viewModel.signIn(
                    editTextUser.text.toString(),
                    editTextPassword.text.toString()
                )
            }
        }
    }

    private fun toStatementActivity(userData: UiLoginModel) {
        val intent = Intent(this, StatementActivity::class.java)
        intent.putExtra(Constants.KeyExtra.KEY_EXTRA_LOGIN, userData)
        startActivity(intent)
        finish()
    }

    private fun observeUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.localUser.collect {
                    binding.editTextUser.setText(it)
                }
            }
        }
    }

    private fun observeVMEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.login.collect { loginState ->
                    when (loginState) {
                        is LoginState.Success -> {
                            toStatementActivity(loginState.data)
                        }

                        is LoginState.Error -> {
                            //showError() //error server
                            hideCheckFieldError()
                            Toast.makeText(
                                this@LoginActivity, "Tente Novamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is LoginState.Default -> {
                            hideCheckFieldError()
                        }

                        is LoginState.CheckFieldError -> {
                            showCheckFieldError()
                        }
                    }
                }
            }
        }
    }

    private fun hideCheckFieldError() {
        binding.warning.visibility = View.GONE
    }

    private fun showCheckFieldError() {
        binding.warning.visibility = View.VISIBLE
    }
}

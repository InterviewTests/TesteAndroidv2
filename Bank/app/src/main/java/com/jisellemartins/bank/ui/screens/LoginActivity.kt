package com.jisellemartins.bank.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jisellemartins.bank.databinding.ActivityLoginBinding
import com.jisellemartins.bank.model.Credencials
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.network.Output
import com.jisellemartins.bank.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.credencials = Credencials()


        loginViewModel.liveData.observe(this) {
        binding.isloading = false
            when (it) {
                is Output.Success -> {
                    if (!it.data?.id.equals("0")) {
                        val intent = Intent(this, StatementsActivity::class.java)
                        startActivity(intent)
                    } else {
                        it.data?.message?.let { message -> showToast(message, this) }
                    }
                }
                is Output.Error ->{
                    it.data?.message?.let { message -> showToast(message, this) }
                }
                is Output.ErrorT -> {
                    it.error.message?.let { error -> showToast(error, this) }

                }
                else -> binding.isloading = true
            }
        }
        binding.apply {
            btnLogin.setOnClickListener {
                credencials?.apply {
                    loginViewModel.checkFields(Login(binding.editTextUser.text.toString(), binding.editTextPassword.text.toString()))
                }
            }
        }
    }

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}
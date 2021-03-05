package br.com.silas.testeandroidv2.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.silas.testeandroidv2.R
import br.com.silas.testeandroidv2.databinding.ActivityLoginBinding
import br.com.silas.testeandroidv2.ui.statements.StatementsActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by inject()
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        fetchLogin()
        observerUser()
        observerLoading()
        observerErrorLogin()
//        observerError()
    }


    private fun fetchLogin() {
        binding.buttonLogin.setOnClickListener {
            loginViewModel.fetUser(
                binding.textInputLogin.text.toString(),
                binding.textInputPassword.text.toString()
            )
        }
    }

    private fun observerUser() {
        loginViewModel.result.observe(this, Observer {
            StatementsActivity.start(this, it)
//            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun observerLoading() {
        loginViewModel.loading.observe(this, Observer {
            when (it) {
                true -> {
                    binding.buttonLogin.visibility = View.GONE
                    binding.progressBarLogin.visibility = View.VISIBLE
                }
                false -> {
                    binding.buttonLogin.visibility = View.VISIBLE
                    binding.progressBarLogin.visibility = View.GONE
                }
            }
        })
    }

    private fun observerErrorLogin() {
        loginViewModel.errorLogin.observe(this, Observer {
            Toast.makeText(this,  it.message, Toast.LENGTH_LONG).show()
        })
    }
}
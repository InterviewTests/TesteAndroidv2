package com.example.desafiosantander.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.desafiosantander.R
import com.example.desafiosantander.data.model.basic.ViewModelState
import com.example.desafiosantander.extensions.disable
import com.example.desafiosantander.extensions.enable
import com.example.desafiosantander.extensions.isCpfOrEmail
import com.example.desafiosantander.extensions.isPassword
import com.example.desafiosantander.feature.summary.SummaryActivity
import kotlinx.android.synthetic.main.activity_main.btLogin
import kotlinx.android.synthetic.main.activity_main.edtPassword
import kotlinx.android.synthetic.main.activity_main.edtUser
import kotlinx.android.synthetic.main.activity_main.loading
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        listener()
    }

    private fun validateLogin() {
        var isValidLogin = true

        val user = edtUser.text.toString()
        val password = edtPassword.text.toString()

        if (!user.isCpfOrEmail()) {
            isValidLogin = false
            edtUser.error = getString(R.string.invalid_email_cpf)
        }
        if (!password.isPassword()) {
            isValidLogin = false
            edtPassword.error = getString(R.string.invalid_password)
        }

        if (isValidLogin) {
            viewModel.login(user, password)
        }
    }

    private fun observeLiveData() {
        viewModel.getUserSavedData().observe(this, Observer {
            edtUser.setText(it)
        })

        viewModel.getLiveData().observe(this, Observer {
            with(it) {
                when (status) {
                    ViewModelState.Status.LOADING -> {
                        btLogin.disable()
                        edtUser.disable()
                        edtPassword.disable()
                        loading.visibility = View.VISIBLE
                    }
                    ViewModelState.Status.SUCCESS -> {
                        btLogin.enable()
                        edtUser.enable()
                        edtPassword.enable()
                        loading.visibility = View.GONE

                        finish()
                        startActivity(Intent(this@LoginActivity, SummaryActivity::class.java))
                    }
                    ViewModelState.Status.ERROR -> {
                        btLogin.enable()
                        edtUser.enable()
                        edtPassword.enable()
                        loading.visibility = View.GONE

                        Toast.makeText(this@LoginActivity, it.errors?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        lifecycle.addObserver(viewModel)
    }

    private fun listener() {
        btLogin.setOnClickListener {
            validateLogin()
        }
    }
}

package br.com.bankapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.bankapp.BaseApplication
import br.com.bankapp.R
import br.com.bankapp.commons.Error
import br.com.bankapp.commons.Loading
import br.com.bankapp.commons.Success
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.ui.main.MainActivity
import br.com.bankapp.utils.applyLayoutValidation
import br.com.bankapp.utils.isValidForLogin
import kotlinx.android.synthetic.main.activity_login.*
import java.net.UnknownHostException
import javax.inject.Inject

class LoginActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        val loginComponent = (application as BaseApplication).appComponent
            .loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
        subscribeObserver()
    }

    private fun initComponents() {
        input_user?.apply {
            addTextChangedListener(applyLayoutValidation(input_user_layout))
        }
        input_password?.apply {
            addTextChangedListener(applyLayoutValidation(input_password_layout))
        }
        login_button.setOnClickListener {
            login()
        }
        if (sharedPrefsHelper.hasKey(SharedPrefsHelper.PREF_USER)) {
            val userPassword = sharedPrefsHelper.get(SharedPrefsHelper.PREF_USER, ":")?.split(":")
            input_user.setText(userPassword!![0])
            input_password.setText(userPassword[1])
        }
    }

    private fun login() {
        if (isValidForLogin(this, input_user_layout, input_password_layout)) {
            viewModel.executeAttemptLogin(input_user.text.toString(), input_password.text.toString())
        }
    }

    private fun subscribeObserver() {
        viewModel.uiState.observe(this, Observer {
            when (it) {
                is Loading -> displayLoadingState()
                is Success -> {
                    navMainActivity()
                    hideLoadingState()
                }
                is Error -> {
                    hideLoadingState()
                    val text = if (it.error is UnknownHostException) {
                        getString(R.string.text_connection_unavailable)
                    } else {
                        getString(R.string.text_generic_error)
                    }
                    val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        })
    }

    private fun navMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun displayLoadingState() {
        login_layout.visibility = View.GONE
        login_button.visibility = View.GONE
        login_progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        login_layout.visibility = View.VISIBLE
        login_button.visibility = View.VISIBLE
        login_progress_bar.visibility = View.GONE
    }
}
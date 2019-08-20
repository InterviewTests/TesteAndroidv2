package com.example.mybank.screens.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mybank.R
import com.example.mybank.ViewModelProviderFactory
import com.example.mybank.screens.accountDetail.AccountDetailActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var vmLogin: ViewModelProviderFactory<LoginViewModel>
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupObservers()
        setupScreenEvets()
    }

    private fun setupObservers() {
        viewModel = ViewModelProviders.of(this, vmLogin).get(LoginViewModel::class.java)

        viewModel.loggedInd.observe(this, Observer {
            if (it) {
                val intent = Intent(this, AccountDetailActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                login.visibility = View.VISIBLE
            }
        })

        viewModel.error.observe(this, Observer {
            login.visibility = View.VISIBLE
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                progress_login.visibility = View.VISIBLE
            } else {
                progress_login.visibility = View.GONE
            }
        })
    }

    fun setupScreenEvets() {
        login.setOnClickListener {
            login.visibility = View.GONE
            val username = username.text.toString()
            val password = password.text.toString()

            viewModel.login(username, password)
        }
    }
}

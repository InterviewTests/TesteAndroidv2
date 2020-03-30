package com.example.bankapp.features.login.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp.R
import com.example.bankapp.databinding.ActivityLoginBinding
import com.example.bankapp.features.details.presentantion.DetailsActivity
import com.example.bankapp.features.login.model.UserAccount
import com.example.base.extensions.hideToolbar
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginActivityViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(LoginActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        hideToolbar()
        setUpListener()
    }

    private fun setUpListener() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                is ScreenState.Login -> openDetails(it.userAccount)
                is ScreenState.Error -> showError(it.message)
            }
        })
    }

    private fun openDetails(id: UserAccount) {
        startActivity(DetailsActivity.createIntent(this, id))
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

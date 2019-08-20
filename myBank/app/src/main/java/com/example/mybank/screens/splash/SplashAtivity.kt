package com.example.mybank.screens.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mybank.R
import com.example.mybank.ViewModelProviderFactory
import com.example.mybank.screens.accountDetail.AccountDetailActivity
import com.example.mybank.screens.login.LoginActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashAtivity : AppCompatActivity() {

    @Inject
    lateinit var vmSplash: ViewModelProviderFactory<SplashViewModel>
    lateinit var viewModel: SplashViewModel
    private val HANDLE_TIMER = 700

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel = ViewModelProviders.of(this, vmSplash).get(SplashViewModel::class.java)

        viewModel.user.observe(this, Observer {
            if (it != null) {
                openACtivity(AccountDetailActivity::class.java)
            } else {
                openACtivity(LoginActivity::class.java)
            }
        })
    }

    private fun openACtivity(activity: Class<out Activity>) {
        Handler().postDelayed({
            val intent = Intent(this, activity)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }, HANDLE_TIMER.toLong())
    }
}

package com.paulokeller.bankapp.login

import android.os.Bundle
import com.paulokeller.bankapp.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container_login, LoginFragment.newInstance()).commitNow()
        }
    }
}

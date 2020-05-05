package com.paulokeller.bankapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulokeller.bankapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container_login, LoginFragment.newInstance()).commitNow()
        }
    }
}

package com.paulokeller.bankapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paulokeller.bankapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment.newInstance()).commitNow()
        }
    }
}

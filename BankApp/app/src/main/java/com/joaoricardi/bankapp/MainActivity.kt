package com.joaoricardi.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joaoricardi.bankapp.feature.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragment()
    }

    private fun setUpFragment(){
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.container,
                        LoginFragment()
                )
                .addToBackStack(LoginFragment.TAG)
                .commit()
    }
}

package com.schaefer.bankapp.login_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.schaefer.bankapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {

        }
    }
}
package com.example.santantest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.santantest.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        setEvents()
    }

    fun setEvents(){




        btLogin.setOnClickListener {

            var user = etUser.text.toString()
            var pwd = etPassword.text.toString()



        }

    }





}

package com.example.santandertestev2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.santandertestev2.R

class LoginActivity : AppCompatActivity() {

    val baseUrl = "https://bank-app-test.herokuapp.com/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



    }
}

package com.example.otavioaugusto.testesantander.statements

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.login.LoginContrato
import com.example.otavioaugusto.testesantander.model.UserAccount
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)



        var intent = intent
        val name = intent.getStringExtra("name")
        val bankAccount = intent.getStringExtra("bankAccount")
        val agency = intent.getStringExtra("agency")
        val balance = intent.getDoubleExtra("balance",0.0)

        txtNome.setText(name)
        txtConta.setText(bankAccount+ "/" +agency)
        txtSaldo.setText("R$ "+balance.toString())


    }
}



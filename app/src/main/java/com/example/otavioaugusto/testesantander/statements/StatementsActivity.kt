package com.example.otavioaugusto.testesantander.statements

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
        val subtitulo = intent.getStringExtra("subtitulo")
        val descricao = intent.getStringExtra("descricao")
        val urlImg = intent.getStringExtra("img")

        txtNome.setText(name)


    }
}

package com.example.otavioaugusto.testesantander.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.otavioaugusto.testesantander.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContrato.View {

    lateinit var presenter:LoginContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

     btnLogin.setOnClickListener {
         var user = edtUser.text.toString()
         var password = edtPassword.text.toString()

        if( presenter.validar(user,password)){

            Log.e("Passar para tela","xxz")
        }
     }




    }

    override fun mensagensErro(msg: String) {
        Log.e("Erro","${msg}")
    }



    override fun mensagemOk(msg: String) {
        Log.e("OK","${msg}")
    }




}

package com.example.otavioaugusto.testesantander.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.model.UserAccount
import com.example.otavioaugusto.testesantander.statements.StatementsPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContrato.View {


   override fun user(user: UserAccount) {
       StatementsPresenter.dadosParaIntent(user.userId,
           user.name,user.bankAccount,user.agency,user.balance, this)
    }

    lateinit var presenter:LoginContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

     btnLogin.setOnClickListener {
         var user = edtUser.text.toString()
         var password = edtPassword.text.toString()


            presenter.login(user,password)

     }




    }

    override fun mensagensErro(msg: String) {
        Log.e("Erro","${msg}")
    }



    override fun mensagemOk(msg: String) {
        Log.e("OK","${msg}")
    }




}

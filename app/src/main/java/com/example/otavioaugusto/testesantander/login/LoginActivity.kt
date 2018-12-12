package com.example.otavioaugusto.testesantander.login


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.util.Log

import android.view.View

import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.dao.Dao
import com.example.otavioaugusto.testesantander.model.UserAccount
import com.example.otavioaugusto.testesantander.statements.StatementsPresenter
import com.example.otavioaugusto.testesantander.utils.InternetConnectionUtil
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContrato.View {


    lateinit var presenter:LoginContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Hawk.init(this).build()

        edtUser.text = Hawk.get("user")
        edtPassword.text = Hawk.get("password")

        presenter = LoginPresenter(this)

        btnLogin.setOnClickListener {
            var user = edtUser.text.toString()
            var password = edtPassword.text.toString()
            val isConnected = InternetConnectionUtil.isAnyInternetConnected(this)

            if (!isConnected){
                alertDialog(getString(R.string.erroInternet))

            }else{
                var validar = presenter.validar(user,password)
                if (validar ){
                    presenter.login(user,password)
                    salvarDados(user,password)
                }
            }
        }

    }


    override fun user(user: UserAccount) {
        StatementsPresenter.dadosParaIntent(user.userId,
            user.name,user.bankAccount,user.agency,user.balance, this)
    }


    override fun mensagensErro(msg: String) {
        alertDialog(msg)
    }



    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }


    fun alertDialog(msg:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Importante!")
        builder.setMessage(msg)
        builder.setPositiveButton("OK"){dialog, which ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun salvarDados(user:String, pass:String){
        Dao.salvarDados(user,pass)
    }


}




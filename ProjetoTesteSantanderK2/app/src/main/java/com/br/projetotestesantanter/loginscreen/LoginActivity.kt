package com.br.projetotestesantanter.loginscreen

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.api.model.LoginResponse
import com.br.projetotestesantanter.statementscreen.StatementsActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , LoginContract.View {
    override fun openScreenStatement(loginResponse: LoginResponse) {

        var intent = Intent(this, StatementsActivity::class.java)
        intent.putExtra("login", loginResponse)
        startActivity(intent)
        finish()

    }

    override fun showProgressBar() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progressBar.visibility = View.GONE

    }

    var presenter : LoginPresenter? = null

    override fun getContext(): Context {
        return this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        initListener()

    }

    private fun init() {
        presenter = LoginPresenter()
        presenter?.attachView(this)
        presenter?.setEditUser(edt_user)
        presenter?.setEditPassword(edt_password)
    }

    private fun initListener() {
        btnLogar.setOnClickListener {
            presenter?.logar()
        }
    }

    override fun showErroMsg(msg: String) {

        Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG).show()
    }
}

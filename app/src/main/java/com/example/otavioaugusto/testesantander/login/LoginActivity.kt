package com.example.otavioaugusto.testesantander.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.otavioaugusto.testesantander.R
import com.example.otavioaugusto.testesantander.model.User
import com.example.otavioaugusto.testesantander.model.UserAccount
import com.example.otavioaugusto.testesantander.statements.StatementsActivity
import com.example.otavioaugusto.testesantander.statements.StatementsPresenter
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContrato.View {


    override fun user(user: UserAccount) {
        StatementsPresenter.dadosParaIntent(user.userId,
            user.name,user.bankAccount,user.agency,user.balance, this)
    }

    lateinit var presenter:LoginContrato.Presenter
    var logado: Boolean? = null

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

            var validar = presenter.validar(user,password)

            if (validar ){
                presenter.login(user,password)

                val user = User(user,password)

                Hawk.put("user", user)
                Hawk.put("password", password)

                edtPassword.text = null
                edtUser.text = null

            }

        }




    }

    override fun mensagensErro(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }



    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }





}

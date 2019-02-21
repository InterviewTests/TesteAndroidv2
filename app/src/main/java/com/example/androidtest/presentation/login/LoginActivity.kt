package com.example.androidtest.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.androidtest.R
import com.example.androidtest.presentation.BaseActivity
import com.example.androidtest.presentation.BaseActivityContract
import com.example.androidtest.presentation.currency.CurrencyActivity
import kotlinx.android.synthetic.main.activity_login.*


interface LoginActivityContract : BaseActivityContract {
    fun disableLoading()
    fun showAlert(msg: String)
    fun navigateToHomeActivity()
}

class LoginActivity : BaseActivity(), LoginActivityContract {

    private lateinit var presenter: LoginPresenter
    private lateinit var interactor: LoginInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        interactor = LoginInteractor(presenter)

        btn_login.setOnClickListener {
            interactor.requestLogin(edt_user.text.toString(), edt_pass.text.toString())
        }
    }


    override fun showAlert(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun disableLoading() {

    }

    override fun navigateToHomeActivity() {
        val intent = Intent(this, CurrencyActivity::class.java)
        startActivity(intent)
        finish()
    }

}
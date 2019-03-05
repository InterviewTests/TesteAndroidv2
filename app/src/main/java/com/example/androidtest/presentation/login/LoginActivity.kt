package com.example.androidtest.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.androidtest.R
import com.example.androidtest.presentation.BaseActivity
import com.example.androidtest.presentation.BaseActivityContract
import com.example.androidtest.presentation.currency.CurrencyActivity
import kotlinx.android.synthetic.main.activity_login.*


interface LoginActivityContract : BaseActivityContract {
    fun hideLoading()
    fun showAlert(msg: String)
    fun navigateToHomeActivity()
    fun showLoading()
    fun disableLoginButton()
    fun enableLoginButton()
    fun showLoggedUser(user: String)
}

class LoginActivity : BaseActivity(), LoginActivityContract {

    private lateinit var presenter: LoginPresenterContract
    private lateinit var interactor: LoginInteractorContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        interactor = LoginInteractor(this, presenter)

        btn_login.setOnClickListener {
            interactor.requestLogin(edt_user.text.toString(), edt_pass.text.toString())
        }

        interactor.showLoggedUser()
    }

    override fun showLoggedUser(user: String) {
        edt_user.setText(user)
    }

    override fun showAlert(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun disableLoginButton() {
        btn_login.isEnabled = false
    }

    override fun enableLoginButton() {
        btn_login.isEnabled = true
    }

    override fun navigateToHomeActivity() {
        val intent = Intent(this, CurrencyActivity::class.java)
        startActivity(intent)
        finish()
    }

}
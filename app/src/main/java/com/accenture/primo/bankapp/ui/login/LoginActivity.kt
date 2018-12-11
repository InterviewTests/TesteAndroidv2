package com.accenture.primo.bankapp.ui.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.accenture.primo.bankapp.R
import com.accenture.primo.bankapp.extension.*
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.login.contracts.ILoginActivity
import com.accenture.primo.bankapp.ui.login.contracts.ILoginInteractor
import com.accenture.primo.bankapp.ui.login.contracts.ILoginRouter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginActivity  {
    internal var interactor: ILoginInteractor? = null
    internal var router: ILoginRouter? = null

    override fun getContext(): Context = baseContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginConfigurator.INSTANCE.configure(this)
        btnLogin.setOnClickListener { btnLogin_OnClick() }
    }

    override fun onResume() {
        super.onResume()
        interactor?.readPreferences()
    }

    override fun showPreferences(pref: LoginModel.LoginViewModel) {
        txtUserEdit.setText(pref.login.user)
        txtPasswordEdit.setText(pref.login.password)
    }

    override fun showLoading() {
        pgbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pgbLoading.visibility = View.GONE
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(user: User) {
        router?.showNextScreen(user)
    }

    private fun isValidForm(_user:String, _password:String): Boolean {
        var blnReturn = true
        txtUser.error = null
        txtPassword.error = null

        if(!_user.isCPF() && !_user.isEmail()){
            txtUser.error = resources.getString(R.string.user_error)
            blnReturn = false
        }

        if(!_password.hasOneNumber() || !_password.hasOneSpecialCaracter() || !_password.hasOneUpperCase()){
            txtPassword.error = getResources().getString(R.string.password_error)
            blnReturn = false
        }

        return blnReturn
    }

    private fun btnLogin_OnClick() {
        val strUser = txtUserEdit.text.toString()
        val strPassword = txtPasswordEdit.text.toString()

        if (isValidForm(strUser, strPassword)) {
            interactor?.login(strUser, strPassword)
        }
    }
}

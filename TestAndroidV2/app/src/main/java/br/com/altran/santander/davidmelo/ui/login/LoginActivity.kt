@file:Suppress("DEPRECATION")

package br.com.altran.santander.davidmelo.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import br.com.altran.santander.davidmelo.R
import br.com.altran.santander.davidmelo.ui.account.AccountActivity
import br.com.altran.santander.davidmelo.utils.Helper

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private var p: LoginPresenter<*>? = null
    private var pd: ProgressDialog? = null
    private var user: EditText? = null
    private var pass: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUp()
    }

    override fun makeLogin() {
        p!!.makeLogin(this, user!!.text.toString().trim { it <= ' ' }, pass!!.text.toString().trim { it <= ' ' })
        Helper.openClass(this, AccountActivity::class.java)
    }

    override fun setUp() {
        p = LoginPresenter<LoginContract.View>()
        (p as LoginPresenter<LoginContract.View>).onAttach(this)
        user = findViewById(R.id.login_user)
        pass = findViewById(R.id.login_pass)
    }

    fun onLogin(view: View?) {
        if (user!!.text.toString().trim { it <= ' ' } == "test_user" && pass!!.text.toString().trim { it <= ' ' } == "Test@1") {
            makeLogin()
        } else {
            Snackbar.make(view!!, "Usuário ou senha invalido", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {
        runOnUiThread {
            this.pd = ProgressDialog(this@LoginActivity)
            this.pd!!.setTitle("Aguarde")
            this.pd!!.setMessage("Carregando os dados")
            this.pd!!.setCancelable(true)
            this.pd!!.show()
            val hpd = Handler()
            hpd.postDelayed({ pd!!.dismiss() }, 3000)
        }
    }

    override fun hideProgress() {
        if (pd!!.isShowing) pd!!.dismiss()
    }

    override fun hideKeyboard() {}
}
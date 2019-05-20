package br.com.teste.santander.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.teste.santander.R
import br.com.teste.santander.databinding.ActivityMainBinding
import br.com.teste.santander.login.LoginConfigurator
import br.com.teste.santander.login.interactor.LoginInteractor
import br.com.teste.santander.model.UserAccount
import br.com.teste.santander.statements.view.StatementsActivity

open class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding
    var interactor: LoginInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    override fun init() {
        LoginConfigurator.INSTANCE.configure(this)

        interactor?.verifyLastUser(this)
    }

    override fun setUser(user: String) {
        binding.txtUser.setText(user)
    }

    fun onLoginClick(view: View) {
        disableInteractions()

        binding.progressBar.visibility = View.VISIBLE
        interactor?.doLogin(
            this,
            binding.txtUser.text.toString(),
            binding.txtPassword.text.toString()
        )
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onLoginSuccess(userAccount: UserAccount) {
        enableInteractions()
        binding.progressBar.visibility = View.GONE

        val intent = Intent(this, StatementsActivity::class.java)
        intent.putExtra(StatementsActivity.USER_ACCOUNT_PARAM, userAccount)
        startActivity(intent)
    }

    override fun onLoginError(error: String) {
        enableInteractions()
        binding.progressBar.visibility = View.GONE
        showMessage(error)
    }

    private fun disableInteractions() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun enableInteractions() {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }
}

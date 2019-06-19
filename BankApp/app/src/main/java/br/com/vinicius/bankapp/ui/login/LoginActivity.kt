package br.com.vinicius.bankapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.vinicius.bankapp.R

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadUI()
    }

    fun loadUI(){
        presenter = LoginPresenter(this@LoginActivity)
    }
}

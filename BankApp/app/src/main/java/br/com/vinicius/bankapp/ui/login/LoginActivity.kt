package br.com.vinicius.bankapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.infra.App
import br.com.vinicius.bankapp.infra.SharedPreferences

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadUI()
        verifyLogin()
    }

    private fun loadUI(){
        presenter = LoginPresenter(this@LoginActivity)
    }


    private fun verifyLogin(){
        if (SharedPreferences.isSave())
           goToHome(SharedPreferences.getPreferences())
    }



    private fun goToHome(preferences: User?) {
        if(preferences != null){
            val intent: Intent = Intent()
            startActivity(intent)
            finish()
        }
    }

}

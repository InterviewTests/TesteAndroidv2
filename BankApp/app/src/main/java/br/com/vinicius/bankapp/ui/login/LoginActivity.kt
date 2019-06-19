package br.com.vinicius.bankapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.App
import br.com.vinicius.bankapp.internal.SharedPreferences
import br.com.vinicius.bankapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadUI()
        verifyLogin()
        loadSaveButton()
    }

    private fun loadSaveButton() {
        buttonLogin.setOnClickListener {
            presenter.startLogin(editTextUsername.text.toString(), editTextPassword.text.toString())
        }
    }

    private fun loadUI(){
        presenter = LoginPresenter(this@LoginActivity)
    }


    private fun verifyLogin(){
        if (App.sharedPreferences.isSave())
           goToHome(App.sharedPreferences.getPreferences())
    }


    private fun goToHome(preferences: User?) {
        if(preferences != null){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun notification(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun saveUserPreferences(user: User) {
        App.sharedPreferences.saveUser(user)
    }

}

package br.com.vinicius.bankapp.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.Toast
import androidx.core.os.bundleOf
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.App
import br.com.vinicius.bankapp.internal.Preferences
import br.com.vinicius.bankapp.internal.USER_ACCOUNT
import br.com.vinicius.bankapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter
    private var shortAnimTime:Int = 0

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
        shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
    }


    private fun verifyLogin(){
        if (Preferences.isSave())
           goToHome(Preferences.getPreferences())
    }


    private fun goToHome(preferences: User?) {
        if(preferences != null){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.putExtra(USER_ACCOUNT, preferences)
            startActivity(intent)
            finish()
        }
    }

    override fun notification(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun saveUserPreferences(user: User) {
        Preferences.saveUser(user)
        goToHome(user)
    }

    override fun showProgressBar(show: Boolean) {
        buttonLogin.visibility = if(show) View.INVISIBLE else View.VISIBLE
        progressBarButtonLogin.visibility = if(show) View.VISIBLE else View.GONE
        progressBarButtonLogin.animate().setDuration(shortAnimTime.toLong()).alpha(if(show) 1F else 0F)
            .setListener( object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBarButtonLogin.visibility = if(show) View.VISIBLE else View.GONE
                }
            })
    }

}


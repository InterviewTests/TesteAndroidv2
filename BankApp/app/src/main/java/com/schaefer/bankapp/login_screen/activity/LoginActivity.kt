package com.schaefer.bankapp.login_screen.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.schaefer.bankapp.R
import com.schaefer.bankapp.currency_screen.activity.CurrencyActivity
import com.schaefer.bankapp.login_screen.LoginConfigurator
import com.schaefer.bankapp.login_screen.presenter.LoginPresenterInput
import com.schaefer.bankapp.login_screen.router.LoginRouter
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.user.UserModel
import com.schaefer.bankapp.util.Constants
import com.schaefer.bankapp.util.Constants.Companion.EXTRA_USER
import com.schaefer.bankapp.util.Constants.Companion.SHARED_HAS_LOGIN
import com.schaefer.bankapp.util.Constants.Companion.SHARED_LOGIN
import com.schaefer.bankapp.util.Constants.Companion.SHARED_USERNAME
import com.schaefer.bankapp.util.shared_preferences.SecureSharedPreferences
import com.schaefer.bankapp.util.shared_preferences.SharedUtils.Companion.saveShared
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.frame_progress.*

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    lateinit var toast: Toast
    var presenter: LoginPresenterInput? = null
    var router: LoginRouter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enableClickButton(false)

        LoginConfigurator.INSTANCE.configure(this)
        presenter?.checkLastUser()

        button_login.setOnClickListener {
            presenter?.makeLogin(
                LoginModel(
                    textEditText_email.text.toString(),
                    textEditText_password.text.toString()
                )
            )
        }
        textEditText_password.addTextChangedListener(textWatcher)
        textEditText_email.addTextChangedListener(textWatcher)

        scrollView_login.requestFocus()
    }

    override fun showProgress() {
        constraint_login.visibility = View.GONE
        frame_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        frame_progress.visibility = View.GONE
        constraint_login.visibility = View.VISIBLE
    }

    override fun noUserLogged() {
        enableClickButton(true)
    }

    private fun enableClickButton(isEnable: Boolean) {
        if (isEnable) {
            button_login.isEnabled = true
            button_login.alpha = 1.0F
        } else {
            button_login.isEnabled = false
            button_login.alpha = 0.3F
        }
    }

    override fun successLogin(userModel: UserModel) {
        val intentStatement = Intent(this, CurrencyActivity::class.java)
        intentStatement.putExtra(EXTRA_USER, userModel)
        saveUser(userModel.name)
        router?.passDataToNextScene(intentStatement)
    }

    private fun saveUser(name: String) {
        saveShared(name, SHARED_LOGIN, SHARED_USERNAME, applicationContext)
        saveShared("true", SHARED_LOGIN, SHARED_HAS_LOGIN, applicationContext)
        if (textEditText_password.text.toString().isNotEmpty()) {
            SecureSharedPreferences(
                this,
                SHARED_LOGIN,
                resources.getString(R.string.key_password), true
            ).put(
                Constants.SHARED_PASSWORD, textEditText_password.text.toString()
            )
        }
    }

    override fun errorLogin(errorResult: ErrorResult) {
        enableClickButton(true)
        toast = Toast.makeText(this, errorResult.message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun errorGeneric(message: String) {
        enableClickButton(true)
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    fun isNotEmptyInput(): Boolean {
        return (textEditText_password.text.toString().isNotEmpty() && textEditText_email.text.toString().isNotEmpty())
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            enableClickButton(isNotEmptyInput())
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) this.overridePendingTransition(R.anim.animation_enterback, R.anim.animation_back)
    }
}
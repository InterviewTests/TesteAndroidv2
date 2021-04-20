package com.example.appbanksantander_accenturetalentacquisition.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.appbanksantander_accenturetalentacquisition.MainActivity
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel
import com.example.appbanksantander_accenturetalentacquisition.Presenter.Login.LoginContract
import com.example.appbanksantander_accenturetalentacquisition.Presenter.Login.LoginPresenter
import com.example.appbanksantander_accenturetalentacquisition.R
import com.example.appbanksantander_accenturetalentacquisition.Utils.Validation

class LoginActiviy : AppCompatActivity(), LoginContract.View {
    lateinit var userEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    lateinit var loginListener: LoginContract.UserActionListener
    lateinit var validation: Validation
    lateinit var progressLoadingUser: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginListener = LoginPresenter(this, this)
        validation = Validation()

        userEditText = findViewById(R.id.userEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        progressLoadingUser = findViewById(R.id.progressLoadingUser)
        loginButton = findViewById(R.id.loginButton)

        verifyUserSaved()

        loginButton.setOnClickListener {
            initMainScreen()
        }

    }

    private fun initMainScreen() {
        val user = userEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        val isUserValid = validation.verifyUser(user)
        val isPasswordValid = validation.verifyPassword(password)

        if (!isUserValid) {
            userEditText.error = getString(R.string.invalidUser)
        }else{
            if (!isPasswordValid){
                passwordEditText.error = getString(R.string.invalidPassword)
            }else{
                loginButton.isEnabled = false
                saveUser()
                loginListener.loadUser()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    progressLoadingUser.outlineAmbientShadowColor = getColor(R.color.primary)
                }
                progressLoadingUser.visibility = View.VISIBLE
            }
        }
    }

    private fun saveUser() {
        val user = userEditText.text.toString().trim()
        loginListener.saveUser(user)
    }

    private fun verifyUserSaved() {
        val user = loginListener.verifyUser()
        userEditText.setText(user)
    }

    override fun UserDetails(userAccountModel: UserAccountModel) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userId", userAccountModel.userId)
        intent.putExtra("name", userAccountModel.name)
        intent.putExtra("bankAccount", userAccountModel.bankAccount)
        intent.putExtra("agency", userAccountModel.agency)
        intent.putExtra("balance", userAccountModel.balance)
        startActivity(intent)
        finish()
    }
}
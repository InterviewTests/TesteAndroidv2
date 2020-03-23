package com.example.ibm_test.clean_code.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.ibm_test.MainApplication
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorInput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityInput, TextWatcher {

    @Inject
    lateinit var loginPresenterInput: LoginPresenterInput

    @Inject
    lateinit var loginInteractorInput: LoginInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as? MainApplication)?.applicationComponent?.inject(this)

        loginPresenterInput.attachLoginInput(this)

        btn_login.setOnClickListener {
            onClickLogin()
        }

        input_edit_user.addTextChangedListener(this)
        input_edit_password.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        input_layout_user.isErrorEnabled = false
        input_layout_password.isErrorEnabled = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


    private fun onClickLogin() {
        val getTextUser = input_edit_user?.text.toString()
        val getTextPassword = input_edit_password?.text.toString()

        loginInteractorInput.validateCredentials(user = getTextUser, password = getTextPassword)
    }

    override fun setupErrorToFieldPassword(text: String){
        input_layout_password.isErrorEnabled = true
        input_layout_password.error = text
    }

    override fun setupErrorToFieldUser(text: String){
        input_layout_user.isErrorEnabled = true
        input_layout_user.error = text
    }
}

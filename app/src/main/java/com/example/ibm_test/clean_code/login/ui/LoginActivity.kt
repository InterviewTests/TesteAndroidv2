package com.example.ibm_test.clean_code.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibm_test.MainApplication
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.home.HomeActivity
import com.example.ibm_test.clean_code.login.interactor.LoginInteractorInput
import com.example.ibm_test.clean_code.login.presenter.LoginPresenterInput
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.utils.gone
import com.example.ibm_test.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(R.layout.activity_main), LoginActivityInput, TextWatcher {

    @Inject
    lateinit var loginPresenterInput: LoginPresenterInput

    @Inject
    lateinit var loginInteractorInput: LoginInteractorInput

    companion object{
        const val USER_INFO_DATA_TO_INTENT = "user_Info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as? MainApplication)?.applicationComponent?.inject(this)

        loginPresenterInput.attachLoginInput(this)
        loginInteractorInput.startApp()

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
        progress_bar_login.show()
        btn_login.gone()
        loginInteractorInput.validateCredentials(user = getTextUser, password = getTextPassword)
    }

    override fun setupErrorToFieldPassword(text: String){
        progress_bar_login.gone()
        btn_login.show()
        input_layout_password.isErrorEnabled = true
        input_layout_password.error = text
    }

    override fun setupErrorToFieldUser(text: String){
        progress_bar_login.gone()
        btn_login.show()
        input_layout_user.isErrorEnabled = true
        input_layout_user.error = text
    }

    override fun loadingLogin(user: String, password: String) {
        loginInteractorInput.executeLogin(user, password)
    }

    override fun messageError(message: String) {
        progress_bar_login.gone()
        loading_data_to_home.gone()
        btn_login.show()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startActivityHome(userInfoData: UserInfoData) {
        progress_bar_login.gone()
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("user_info", USER_INFO_DATA_TO_INTENT)
        startActivity(intent)
    }

    override fun loadingUserFromStorage(user: String, password: String) {
        loading_data_to_home.show()
        loginInteractorInput.executeLogin(user, password)
    }
}

package com.androiddeveloper.santanderTest.ui.login

import android.content.Intent
import android.os.Bundle
import com.androiddeveloper.santanderTest.R
import com.androiddeveloper.santanderTest.data.model.userdata.LoginError
import com.androiddeveloper.santanderTest.manager.EncryptManager
import com.androiddeveloper.santanderTest.shared.base.BaseActivity
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferenceManager
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferencesEnum
import com.androiddeveloper.santanderTest.ui.statements.BankInfoActivity
import com.androiddeveloper.santanderTest.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity(), ILoginContract.LoginActInput {

    var loginInteractor = LoginInteractor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        checkIfUserIsLogged()

        checkLastUsername()

        loginInteractor.bind(this)

        onLoginClick()
    }

    private fun onLoginClick() {
        btn_login.setOnClickListener {
            loginInteractor.isUserValid(et_user_login.text.toString(), et_password_login.text.toString())
        }
    }

    private fun checkIfUserIsLogged() {
        if (SharedPreferenceManager.isUserLogged(this, SharedPreferencesEnum.KEY_USER_LOGGED))
            onLoginSuccess()
    }

    private fun checkLastUsername(){
        if (SharedPreferenceManager.getUsername(this, SharedPreferencesEnum.KEY_USERNAME)?.isNotBlank()!!) {
            val username = SharedPreferenceManager.getUsername(this, SharedPreferencesEnum.KEY_USERNAME)?.let {
                EncryptManager.decryptUsername(it)
            }
            et_user_login.setText(username)
        }
    }

    override fun onLoginSuccess() {
        et_password_login.text?.clear()
        startActivity(Intent(this, BankInfoActivity::class.java))
    }

    override fun onLoginError(err: LoginError) {
        UIUtil.showErrorDialog(this, err.message)
    }

    override fun onInvalidLogin(message: Int) {
        UIUtil.showErrorDialog(this, getString(message))
    }

    override fun onValidLogin(username: String, password: String) {
        loginInteractor.fetchUserData(username, password)
    }
}

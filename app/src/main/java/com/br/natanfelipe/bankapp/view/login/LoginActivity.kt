package com.br.natanfelipe.bankapp.view.login

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ProgressBar
import com.br.natanfelipe.bankapp.R
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.configurator.LoginConfigurator
import com.br.natanfelipe.bankapp.interfaces.home.LoginActivityIntput
import com.br.natanfelipe.bankapp.model.UserAccount
import com.br.natanfelipe.bankapp.router.LoginRouter
import com.br.natanfelipe.bankapp.interfaces.login.LoginInteractorInput
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog

class LoginActivity : AppCompatActivity(),LoginActivityIntput{

    lateinit var username : String
    lateinit var pwd : String
    lateinit var api : RestApi
    var output : LoginInteractorInput? = null
    lateinit var context : Context
    internal var router: LoginRouter? = null
    lateinit var progress : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context = this
        LoginConfigurator.INSTANCE.configure(this)

        til_password.et_pwd.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(til_password.error == getString(R.string.pwd_error_msg)){
                    til_password.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        til_user.et_user.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(til_user.error == getString(R.string.user_error_msg)){
                    til_user.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

        button_login.setOnClickListener {
            api = RestApi()

            username = til_user.et_user.text.toString().trim()
            pwd = til_password.et_pwd.text.toString().trim()

            fetchMetaData(username,pwd)
        }
    }

    fun fetchMetaData(username: String, pwd: String) {
        val loginRequest = LoginRequest()
        loginRequest.api = api

        val isValidField = output?.validateFieldsData(username,pwd)

        if(isValidField == true){
            progress = indeterminateProgressDialog(getString(R.string.lb_carregando))
            progress.show()

            output?.fetchLoginMetaData(loginRequest,username,pwd)
        } else {
            if(output?.validateUsernameData(username) == false){
                til_user.error = getString(R.string.user_error_msg)
            }

            if(output?.validatePwdData(pwd) == false){
                til_password.error = getString(R.string.pwd_error_msg)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(til_user.et_user.text!!.isNotEmpty()) {
            til_user.et_user.setText("")
            til_user.et_user.requestFocus()
        }

        if(til_password.et_pwd.text!!.isNotEmpty()) {
            til_password.et_pwd.setText("")
        }

    }


    override fun displayLoginMetaData(viewModel: LoginViewModel) {


        storeData(viewModel)
        router = LoginRouter()
        var intent = router!!.determineNextScreen(this)
        router!!.passDataToNextScene(viewModel,intent)
        progress.dismiss()
        startActivity(intent)
    }

    private fun storeData(viewModel: LoginViewModel) {
        val editor = getSharedPreferences("userDataPrefs", Context.MODE_PRIVATE).edit()
        editor.putInt("id", viewModel.userAccount.userId)
        editor.putString("name",viewModel.userAccount.name)
        editor.putString("balance",viewModel.userAccount.balance.toString())
        editor.putString("bankAccount",viewModel.userAccount.bankAccount)
        editor.putInt("agency",viewModel.userAccount.agency)
        editor.commit()
    }
}

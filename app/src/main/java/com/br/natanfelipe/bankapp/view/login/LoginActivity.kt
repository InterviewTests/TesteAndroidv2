package com.br.natanfelipe.bankapp.view.login

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.br.natanfelipe.bankapp.R
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.configurator.LoginConfigurator
import com.br.natanfelipe.bankapp.interfaces.home.LoginActivityIntput
import com.br.natanfelipe.bankapp.interfaces.login.LoginInteractorInput
import com.br.natanfelipe.bankapp.router.LoginRouter
import com.br.natanfelipe.bankapp.utils.CriptoDES
import com.br.natanfelipe.bankapp.utils.EncripUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.indeterminateProgressDialog

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

    private fun fetchMetaData(username: String, pwd: String) {
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
        clearFields()
        clearData()
    }

    private fun clearData() {
        val prefs = getSharedPreferences("userDataPrefs", Context.MODE_PRIVATE).edit()
        prefs.putString("id","")
        prefs.putString("name","")
        prefs.putString("balance","")
        prefs.putString("bankAccount","")
        prefs.putString("agency","")
        prefs.commit()
    }


    override fun displayLoginMetaData(viewModel: LoginViewModel) {
        storeData(viewModel)
        router = LoginRouter()
        var intent = router!!.determineNextScreen(this)
        progress.dismiss()
        startActivity(intent)
    }

    private fun clearFields(){
        if(til_user.et_user.text!!.isNotEmpty()) {
            til_user.et_user.setText("")
            til_user.et_user.requestFocus()
        }

        if(til_password.et_pwd.text!!.isNotEmpty()) {
            til_password.et_pwd.setText("")
        }
    }

    private fun storeData(viewModel: LoginViewModel) {
        val editor = getSharedPreferences("userDataPrefs", Context.MODE_PRIVATE).edit()

        editor.putString("id",EncripUtils.encrypt(viewModel.userAccount.userId.toString(),""))
        editor.putString("name",EncripUtils.encrypt(viewModel.userAccount.name,""))
        editor.putString("balance",EncripUtils.encrypt(viewModel.userAccount.balance.toString(),""))
        editor.putString("bankAccount",EncripUtils.encrypt(viewModel.userAccount.bankAccount,""))
        editor.putString("agency",EncripUtils.encrypt(viewModel.userAccount.agency.toString(),""))
        editor.commit()
    }
}

package com.felipemsa.idletime.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.UserAccount
import com.felipemsa.idletime.helper.DataStorage
import com.felipemsa.idletime.ui.statements.StatementsActivity
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    var saveUserData = false

    private val loginViewModel: LoginViewModel by lazy { ViewModelProviders.of(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkLastUser()
        initListeners()
        initObservables()
    }

    private fun checkLastUser() {
        DataStorage.getUser()?.let { user ->
            input_layout_user.editText?.setText(user)
            switch_save_user.isChecked = true
        }
    }

    private fun initListeners() {

        switch_save_user.setOnCheckedChangeListener { _, isChecked ->
            saveUserData = isChecked
        }

        btt_login.setOnClickListener {
            btt_login.isEnabled = false

            val user = input_layout_user.editText?.text.toString()
            val pass = input_layout_pass.editText?.text.toString()

            loginViewModel.login(user, pass)
        }
    }

    private fun initObservables() {
        loginViewModel.loginData.observe(this, Observer<UserAccount> { account ->
            account?.let { acc ->
                //                if (saveUserData)
//                    DataStorage.saveUser(user)

                startActivity(Intent(applicationContext, StatementsActivity::class.java))
                finish()
            }
        })
        loginViewModel.loginError.observe(this, Observer<String> { error ->
            Log.d("ERROR", "error: $error")
            Snackbar.make(main_container, getString(R.string.warning_error_login), Snackbar.LENGTH_LONG).show()
        })
    }
}

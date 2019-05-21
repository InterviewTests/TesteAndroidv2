package com.felipemsa.idletime.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.felipemsa.idletime.R
import com.felipemsa.idletime.helper.DataStorage
import com.felipemsa.idletime.helper.hideKeyboard
import com.felipemsa.idletime.ui.statements.StatementsActivity
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    private var saveUserData = false

    private val loginViewModel by lazy { ViewModelProviders.of(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkLastUser()
        initListeners()
        initObservables()
    }

    private fun checkLastUser() {
        DataStorage.getUser()?.let { user ->
            input_layout_user.editText?.let { et ->
                et.setText(user)
                et.setSelection(et.text.length)
            }
            switch_save_user.isChecked = true
        }
    }

    private fun initListeners() {

        switch_save_user.setOnCheckedChangeListener { _, isChecked ->
            saveUserData = isChecked
        }

        check_pass_visibility.setOnCheckedChangeListener { _, isChecked ->
            input_layout_pass.editText?.let { editText ->
                editText.inputType =
                        if (isChecked) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                editText.setSelection(editText.text.length)
            }
        }

        btt_login.setOnClickListener {
            clickLogin()
        }

        input_layout_user.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val userOK =
                        if (s != null) s.length > 3
                        else false
                val passOK =
                        if (input_layout_pass.editText != null) input_layout_pass.editText!!.text.length > 3
                        else false

                loginViewModel.buttonEnabled.postValue(userOK && passOK)
            }
        })

        input_layout_pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val passOK =
                        if (s != null) s.length > 3
                        else false
                val userOK =
                        if (input_layout_user.editText != null) input_layout_user.editText!!.text.length > 3
                        else false

                loginViewModel.buttonEnabled.postValue(userOK && passOK)
            }
        })

        input_layout_pass.editText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                v.hideKeyboard()
                clickLogin()
                true
            } else {
                false
            }
        }
    }

    private fun initObservables() {
        loginViewModel.saveUser.observe(this, Observer<String> { user ->
            user?.let {
                startActivity(Intent(applicationContext, StatementsActivity::class.java))
                finish()
            }
        })

        loginViewModel.loginError.observe(this, Observer<Int> { errorId ->
            errorId?.let {
                Snackbar.make(main_container, getString(errorId), Snackbar.LENGTH_LONG).show()
                btt_login.isEnabled = true
                login_progress.visibility = View.GONE
            }
        })

        loginViewModel.buttonEnabled.observe(this, Observer<Boolean> { isEnabled ->
            isEnabled?.let {
                btt_login.isEnabled = it
            }
        })
    }

    private fun clickLogin() {
        btt_login.isEnabled = false
        login_progress.visibility = View.VISIBLE

        val user = input_layout_user.editText?.text.toString()
        val pass = input_layout_pass.editText?.text.toString()

        loginViewModel.login(user, pass, saveUserData)
    }
}

package com.felipemsa.idletime.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.felipemsa.idletime.BuildConfig
import com.felipemsa.idletime.R
import com.felipemsa.idletime.data.BankApi
import com.felipemsa.idletime.data.LoginResponse
import com.felipemsa.idletime.helper.DataStorage
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {

    var saveUserData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkLastUser()
        initListeners()
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
            val user = input_layout_user.editText?.text.toString()
            val pass = input_layout_pass.editText?.text.toString()

            input_layout_user.isErrorEnabled = TextUtils.isEmpty(user)
            input_layout_pass.isErrorEnabled = TextUtils.isEmpty(pass)

            if (!input_layout_user.isErrorEnabled && !input_layout_pass.isErrorEnabled) {
                btt_login.isEnabled = false

                BankApi().banckApi().login(user, pass).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            if (saveUserData)
                                DataStorage.saveUser(user)

                            response.body()?.let {
                                DataStorage.setAccount(it.userAccount)
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                finish()
                            }
                        }

                        btt_login.isEnabled = true
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Snackbar
                            .make(main_container, getString(R.string.warning_error_login), Snackbar.LENGTH_LONG)
                            .show()

                        btt_login.isEnabled = true
                    }
                })
            }
        }
    }
}

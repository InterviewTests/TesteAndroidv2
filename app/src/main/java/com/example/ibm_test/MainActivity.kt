package com.example.ibm_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        btn_login.setOnClickListener {
            onClickLogin()
        }
    }

    private fun onClickLogin() {
        val getTextUser = input_user.editText?.text
        val getTextPassword = input_password.editText?.text

        when {
            getTextUser?.trim().isNullOrEmpty() -> {
                input_user.isErrorEnabled = true
                input_user.requestFocus()
                input_user.error = getString(R.string.alert_to_field_empty)
            }
            getTextPassword?.trim().isNullOrEmpty() -> {
                input_password.isErrorEnabled = true
                input_user.requestFocus()
                input_password.error = getString(R.string.alert_to_field_empty)
            }
        }
    }
}

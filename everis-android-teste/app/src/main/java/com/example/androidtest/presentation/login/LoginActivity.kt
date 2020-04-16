package com.example.androidtest.presentation.login

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.androidtest.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.presentation.home.HomeActivity
import com.example.androidtest.utils.Constants
import com.example.androidtest.utils.NetworkInfo
import com.example.androidtest.utils.SharedPreference
import com.example.androidtest.utils.ValidateComponent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var mLoginViewModel: LoginViewModel
    private val mNetworkInfo = NetworkInfo
    val mValidateComponent = ValidateComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        this.mContext = this
        this.mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        buttonLogin.setOnClickListener(this)
        this.createObservers()
    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View?) {

        progressBar.visibility = View.VISIBLE

        val user = editTextUser.text.toString()
        val pass = editTextPass.text.toString()

        if (mValidateComponent.isValidEmail(user) ||
            mValidateComponent.isValidCpf(user))
        {
            val res = mValidateComponent.isValidPassword(pass)
            if (res == "") {
                editTextUser.error = null
                editTextPass.error = null

                if (mNetworkInfo.isNetworkConnected(this)) {
                    this.mLoginViewModel.doLogin(user, pass)

                } else {
                    progressBar.visibility = View.GONE
                    AlertDialog.Builder(this).setTitle("\n" + "Sem conexão com a Internet")
                        .setMessage("Por favor verifique sua conexão com a internet e tente novamente")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert).show()
                }
            } else {
                editTextPass.error = res
                progressBar.visibility = View.GONE
            }
        }
        else
        {
            editTextUser.error = "Digite um Email ou Cpf válido!"
            progressBar.visibility = View.GONE
        }

    }

    private fun createObservers() {
        mLoginViewModel.login().observe(this, Observer {
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
            if (it == "Success") {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(mContext, it, Toast.LENGTH_LONG).show()
            }
        })

        mLoginViewModel.nameUser().observe(this, Observer {
            SharedPreference.post(this, it.toString(), Constants.PREF_NAME)
        })

        mLoginViewModel.contUser().observe(this, Observer {
            SharedPreference.post(this, it.toString(), Constants.PREF_CONT)
        })
    }
}

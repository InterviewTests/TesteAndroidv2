package com.valber.santandertestvalber.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.valber.santandertestvalber.presentation.Resource
import com.valber.data.model.UserAccount
import com.valber.santandertestvalber.databinding.ActivityMainBinding
import com.valber.santandertestvalber.extensions.navigaterWithValue
import com.valber.santandertestvalber.presentation.prensenter.LoginPresenter
import com.valber.santandertestvalber.presentation.LoginViewModel
import com.valber.santandertestvalber.presentation.ResourceState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by currentScope.viewModel(this)
    private var loginActivity: ActivityMainBinding? = null
    val key = "Last_Uset"
    private val preferences by lazy {  getSharedPreferences(key, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity = DataBindingUtil.setContentView(this, com.valber.santandertestvalber.R.layout.activity_main)
        logar()
    }

    private fun logar() {
        loginActivity?.presenter = object :
            LoginPresenter {
            override fun onClick() {
                loginViewModel.logar(user_login.text.toString(), password_login.text.toString())
                loginViewModel.login.observe(this@LoginActivity, Observer { updateUi(it) })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        get()
    }

    private fun updateUi(resource: Resource<UserAccount>) {
        resource?.let { it ->
            when (it.state) {
                ResourceState.LOADING -> print("carregango..")
                ResourceState.SUCCESS -> it.data?.let { userAccount ->
                    set(user_login.text.toString())
                    password_login.setText("")
                    this.navigaterWithValue(userAccount)
                }
                ResourceState.ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun get(){
        user_login.setText(preferences.getString(key, ""))
    }

    fun set(userCache: String) {
        with(preferences.edit()) {
            putString(key, userCache)
            commit()
        }
    }

}

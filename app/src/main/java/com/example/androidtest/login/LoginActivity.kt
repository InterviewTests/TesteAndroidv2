package com.example.androidtest.login

import com.example.androidtest.BaseActivity
import com.example.androidtest.BaseActivityContract


interface LoginActivityContract : BaseActivityContract {
    fun disableLoading()
    fun showAlert(msg: String)
    fun navigateToHomeActivity()
}

class LoginActivity : BaseActivity(), LoginActivityContract {

    override fun showAlert(msg: String) {

    }

    override fun disableLoading() {

    }

    override fun navigateToHomeActivity() {

    }

}
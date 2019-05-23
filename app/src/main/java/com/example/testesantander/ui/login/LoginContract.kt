package com.example.testesantander.ui.login

import com.example.testesantander.domain.model.UserData
import com.example.testesantander.mvp.Contract

interface LoginContract{
    interface View: Contract.View{

        fun getAccountData(userData: UserData)
        fun login()
        fun showToast(resId: Int)
        fun callStatements()
        fun onLoading(isLoading: Boolean)
        fun saveLogin()
    }
    interface Presenter: Contract.Presenter<View>{

        fun getUserCase()
        fun checkValues(user: String, password: String)
    }
}
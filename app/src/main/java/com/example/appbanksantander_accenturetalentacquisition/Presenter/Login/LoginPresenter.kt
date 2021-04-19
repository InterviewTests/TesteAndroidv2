package com.example.appbanksantander_accenturetalentacquisition.Presenter.Login

import android.content.Context
import android.content.SharedPreferences
import com.example.appbanksantander_accenturetalentacquisition.API.RequestApi
import com.example.appbanksantander_accenturetalentacquisition.API.ServiceApi
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel

class LoginPresenter(accountView: LoginContract.View?, private val context: Context?):
    LoginContract.UserActionListener {
    val serviceApi: ServiceApi
    val myAccountView: LoginContract.View?

    init {
        serviceApi = RequestApi(context)
        myAccountView = accountView
    }

    override fun loadUser() {
        serviceApi.getAccountInfo(object: ServiceApi.ServiceApiCallbackUser<UserAccountModel>{
            override fun loaded(userAccount: UserAccountModel) {
                myAccountView?.UserDetails(userAccount!!)
            }
        })
    }

    override fun saveUser(user: String) {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("haveUser", true)
        editor.putString("user", user)
        editor.apply()
    }

    override fun verifyUser(): String {
        var haveUserAccount = ""
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        sharedPreferences.getBoolean("haveUser", false)
        if (sharedPreferences.contains("haveUser")){
            haveUserAccount = sharedPreferences.getString("user", "").toString()
            return haveUserAccount
        }
        return haveUserAccount
    }

}
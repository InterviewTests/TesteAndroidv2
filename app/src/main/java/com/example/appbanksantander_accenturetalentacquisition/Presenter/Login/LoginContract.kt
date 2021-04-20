package com.example.appbanksantander_accenturetalentacquisition.Presenter.Login

import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel

interface LoginContract {
    interface View{
        fun UserDetails(userAccountModel: UserAccountModel)
    }
    interface UserActionListener{
        fun loadUser()
        fun saveUser(user: String)
        fun verifyUser(): String
    }
}
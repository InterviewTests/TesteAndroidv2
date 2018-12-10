package com.accenture.primo.bankapp.data.local

import com.accenture.primo.bankapp.ui.login.LoginModel

interface IUserPreferences {
    fun readPreferences(): LoginModel.LoginViewModel?
    fun savePreferences(pref: LoginModel.LoginViewModel)
}
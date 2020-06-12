package com.appdesafioSantander.bank.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import com.appdesafioSantander.bank.model.Login

interface LoginListener {
    fun onStarted(view: View) {}
    fun onSuccess(view: View, login: LiveData<Login>) {}
    fun onFailure(view: View, msg: String) {}
}
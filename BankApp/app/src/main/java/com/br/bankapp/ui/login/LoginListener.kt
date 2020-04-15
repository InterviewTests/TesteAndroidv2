package com.br.bankapp.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import com.br.bankapp.model.LoginResponse

interface LoginListener {
    fun onStarted(view: View) {}
    fun onSuccess(view: View, loginResponse: LiveData<LoginResponse>) {}
    fun onFailure(view: View, msg: String) {}
}
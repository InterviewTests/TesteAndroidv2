package com.solinftec.desafiosantander_rafaelpimenta.util

import android.view.View
import androidx.lifecycle.LiveData
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse

interface LoginListener {
    fun onStarted(view: View) {}
    fun onSuccess(view: View, login: LiveData<LoginResponse>) {}
    fun onFailure(view: View, msg: String) {}
}
package com.solinftec.desafiosantander_rafaelpimenta.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {


    var usuario = ObservableField<String>()
    var senha = ObservableField<String>()


    fun login() {

        val user = usuario.get()
        val passwd = senha.get()



    }
}
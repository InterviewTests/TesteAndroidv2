package com.rafaelpereiraramos.testeandroidv2.view

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class LoginActivityViewModel @Inject constructor() : ViewModel() {

    fun login(userName: String, password: String): Boolean {
        return false
    }
}
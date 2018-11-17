package com.rafaelpereiraramos.testeandroidv2.view

import androidx.lifecycle.ViewModel
import com.rafaelpereiraramos.testeandroidv2.repo.UserRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class LoginActivityViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel() {

    fun login(userName: String, password: String): Boolean {
        return false
    }
}
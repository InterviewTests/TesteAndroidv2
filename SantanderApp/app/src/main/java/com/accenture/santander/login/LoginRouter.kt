package com.accenture.santander.login

import android.view.View
import androidx.navigation.findNavController
import com.accenture.santander.R

class LoginRouter(private val view: View) {

    fun navigationToDashBoard() {
        view.findNavController().navigate(R.id.action_loginFragment_to_dashBoardFragment)
    }

}
package com.accenture.santander.login

import android.view.View
import androidx.navigation.findNavController
import com.accenture.santander.R
import org.junit.Assert.*
import org.junit.Test

class LoginRouter(private val view: View) {

    @Test
    fun navigationToDashBoard() {
        assertNotNull(view)
        view.findNavController().navigate(R.id.action_loginFragment_to_dashBoardFragment)
    }

}
package com.accenture.santander.dashBoard

import android.view.View
import androidx.navigation.findNavController
import com.accenture.santander.R

class DashBoardRouter(private val view: View) {

    fun popBackStack() {
        view.findNavController().popBackStack()
    }

}
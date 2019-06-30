package com.accenture.santander.statements

import android.view.View
import androidx.navigation.findNavController

class StatementRouter(private val view: View) {

    fun popBackStack() = view.findNavController().popBackStack()

}
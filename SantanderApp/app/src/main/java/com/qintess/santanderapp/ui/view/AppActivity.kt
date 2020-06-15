package com.qintess.santanderapp.ui.view

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class AppActivity : AppCompatActivity() {
    fun showAlert(title: String, msg: String): Boolean {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(msg)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
        return true // somente para saber que toda execução acima aconteceu sem erros
    }
}
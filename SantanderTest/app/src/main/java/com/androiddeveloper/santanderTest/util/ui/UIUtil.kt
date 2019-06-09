package com.androiddeveloper.santanderTest.util.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object UIUtil {

    fun showErrorDialog(context: Context, message: String): AlertDialog? {
        return MaterialAlertDialogBuilder(context)
                .setCancelable(true)
                .setTitle("Erro")
                .setMessage(message)
                .setNegativeButton("Fechar") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
    }
}
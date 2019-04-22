package br.com.bankapp.util

import android.content.Context
import android.support.v7.app.AlertDialog

class Alert {
    companion object {
        fun alertSimples(context: Context, mensagem: String){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Atenção")
            builder.setMessage(mensagem)
            builder.setPositiveButton("OK") { dialog, which -> }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}
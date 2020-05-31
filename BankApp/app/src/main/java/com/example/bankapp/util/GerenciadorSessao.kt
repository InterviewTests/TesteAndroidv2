package com.example.bankapp.util

import android.app.Application
import android.content.SharedPreferences

class GerenciadorSessao(val app: Application) {
    val sharedPreferences: String = "shared_preferences"

    fun limparDados() {
        val sharedPrefs: SharedPreferences = app.getSharedPreferences(sharedPreferences, 0)

        val editor: SharedPreferences.Editor = sharedPrefs.edit()

        editor.clear()
        editor.apply()
    }

    fun salvarInformacoesUsuario(
        id: Int?,
        nome: String?,
        conta: String?,
        agencia: String?,
        saldo: Double?
    ) {
        val sharedPrefs: SharedPreferences = app.getSharedPreferences(sharedPreferences, 0)
        val editor = sharedPrefs.edit()
        editor.putString(Constantes.Parametros.NAME, nome)
        editor.putString(Constantes.Parametros.BANK_ACCOUNT, conta)
        editor.putString(Constantes.Parametros.AGENCY, agencia)
        editor.putFloat(Constantes.Parametros.BALANCE, saldo!!.toFloat())
        editor.apply()

    }


}
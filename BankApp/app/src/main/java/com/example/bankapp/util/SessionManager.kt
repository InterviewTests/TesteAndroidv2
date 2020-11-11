package com.example.bankapp.util

import android.app.Application
import android.content.SharedPreferences
import com.example.domain.entities.ContaUsuario

class SessionManager(val app: Application) {
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
        editor.putString(Constants.Parametros.NAME, nome)
        editor.putString(Constants.Parametros.BANK_ACCOUNT, conta)
        editor.putString(Constants.Parametros.AGENCY, agencia)
        editor.putFloat(Constants.Parametros.BALANCE, saldo!!.toFloat())
        editor.putInt(Constants.Parametros.ID, id!!)
        editor.apply()

    }

    fun retornarUsuario(): ContaUsuario? {
        val sharedPrefs: SharedPreferences = app.getSharedPreferences(sharedPreferences, 0)
        val idUsuario = sharedPrefs.getInt(Constants.Parametros.ID, -1)
        val conta = sharedPrefs.getString(Constants.Parametros.BANK_ACCOUNT, "")
        val agencia = sharedPrefs.getString(Constants.Parametros.AGENCY, "")
        val nome = sharedPrefs.getString(Constants.Parametros.NAME, "")
        val saldo = sharedPrefs.getFloat(Constants.Parametros.BALANCE, 0.0f)

        return ContaUsuario(
            id = idUsuario,
            conta = conta,
            agencia = agencia,
            nome = nome,
            saldo = saldo.toDouble()
        )
    }


}
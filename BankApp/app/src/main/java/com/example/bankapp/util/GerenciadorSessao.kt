package com.example.bankapp.util

import android.app.Application
import android.content.SharedPreferences
import com.example.domain.entidades.ContaUsuario

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
        editor.putInt(Constantes.Parametros.ID, id!!)
        editor.apply()

    }

    fun retornarUsuario(): ContaUsuario? {
        val sharedPrefs: SharedPreferences = app.getSharedPreferences(sharedPreferences, 0)
        val idUsuario = sharedPrefs.getInt(Constantes.Parametros.ID, -1)
        val conta = sharedPrefs.getString(Constantes.Parametros.BANK_ACCOUNT, "")
        val agencia = sharedPrefs.getString(Constantes.Parametros.AGENCY, "")
        val nome = sharedPrefs.getString(Constantes.Parametros.NAME, "")
        val saldo = sharedPrefs.getFloat(Constantes.Parametros.BALANCE, 0.0f)

        return ContaUsuario(
            id = idUsuario,
            conta = conta,
            agencia = agencia,
            nome = nome,
            saldo = saldo.toDouble()
        )
    }


}
package br.com.projetoaccenturebank.model

import android.app.Activity
import android.content.Context
import br.com.projetoaccenturebank.view.LoginActivity
import br.com.projetoaccenturebank.login.R
import br.com.projetoaccenturebank.view.ActivityPrincipal
import br.com.projetoaccenturebank.util.SendIntent
import com.google.gson.Gson
import java.io.Serializable
import java.lang.Exception

class Login : Serializable {

    var PREFS_LOGIN: String = "prefs_usuario"
    var PREFS_LOGIN_DADOS: String = "prefs_usuario_dados"

    var userId : Int? = null
    var name : String? = null
    var bankAccount : Int? = null
    var agency : Int? = null
    var balance : Float? = null

    /**
     * RETORAR LOGIN GRAVADO
     */

    fun retornar(context: Context): Login? {
        val prefs = context.getSharedPreferences(PREFS_LOGIN, Context.MODE_PRIVATE)

        val json = prefs.getString(PREFS_LOGIN_DADOS, "")
        val login = Gson().fromJson(json, Login::class.java)

        return login
    }

    @Throws(Exception::class)
    fun iniciar(context: Context, login: Login, direcionar: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_LOGIN, Context.MODE_PRIVATE)
        val json = prefs.getString(PREFS_LOGIN_DADOS, "")
        val loginJson = Gson().fromJson(json, Login::class.java)

        if (loginJson != null)
            prefs.edit().clear().apply()

        val editor = prefs.edit()
        editor.putString(PREFS_LOGIN_DADOS, Gson().toJson(login))
        editor.apply()

        if (direcionar)
            SendIntent.with()
                .mClassFrom(context as Activity)
                .mClassTo(ActivityPrincipal::class.java)
                .mType(R.integer.slide_from_right)
                .go()
    }

    @Throws(Exception::class)
    fun deslogar(context: Context, redirecionar: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_LOGIN, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()

        if (redirecionar)
            SendIntent.with()
                .mClassFrom(context as Activity)
                .mClassTo(LoginActivity::class.java)
                .mType(R.integer.slide_from_left)
                .go()
    }
}
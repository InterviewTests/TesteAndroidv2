package br.com.teste.santander.model

import android.app.Activity
import android.content.Context
import br.com.teste.santander.R
import br.com.teste.santander.principal.ActivityLogin
import br.com.teste.santander.principal.ActivityPrincipal
import br.com.teste.santander.util.SendIntent
import com.google.gson.Gson
import java.io.Serializable
import java.lang.Exception

class Login : Serializable {

    var LOGIN: String = "us"
    var PREFS_LOGIN_DADOS: String = "prefs_usuario_dados"

    var userId : Int? = null
    var name : String? = null
    var bankAccount : Int? = null
    var agency : Int? = null
    var balance : Float? = null

    /**
     * RETORAR LOGIN GRAVADO
     */

    fun returnLogin(context: Context): Login? {
        val prefs = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE)

        val json = prefs.getString(PREFS_LOGIN_DADOS, "")

        return Gson().fromJson(json, Login::class.java)
    }

    @Throws(Exception::class)
    fun init(context: Context, login: Login, direcionar: Boolean) {
        val prefs = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE)
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
    fun logout(context: Context, redirecionar: Boolean) {
        val prefs = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()

        if (redirecionar)
            SendIntent.with()
                .mClassFrom(context as Activity)
                .mClassTo(ActivityLogin::class.java)
                .mType(R.integer.slide_from_left)
                .go()
    }
}
package br.com.teste.santander.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.Exception

class Dados : Serializable{
    var PREFS_STATEMENT: String = "prefs_statement"
    var PREFS_STATEMENT_DADOS: String = "prefs_statement_dados"


    var title : String? = null
    var desc : String? = null
    var date : String? = null
    var value : Float? = null


    fun getDados(context: Context): ArrayList<Dados>? {
        val prefs = context.getSharedPreferences(PREFS_STATEMENT, Context.MODE_PRIVATE)

        val json = prefs.getString(PREFS_STATEMENT_DADOS, "")
        val listType = object : TypeToken<ArrayList<Dados>>() {

        }.type

        return Gson().fromJson<ArrayList<Dados>>(json, listType)
    }

    @Throws(Exception::class)
    fun init(context: Context, lista: ArrayList<Dados>) {
        val prefs = context.getSharedPreferences(PREFS_STATEMENT, Context.MODE_PRIVATE)
        val json = prefs.getString(PREFS_STATEMENT_DADOS, "")

        val listType = object : TypeToken<ArrayList<Dados>>() {

        }.type
        val dados = Gson().fromJson<ArrayList<Dados>>(json, listType)

        if (dados != null && dados.size > 0)
            prefs.edit().clear().apply()

        val editor = prefs.edit()
        editor.putString(PREFS_STATEMENT_DADOS, Gson().toJson(lista))
        editor.apply()

    }

}
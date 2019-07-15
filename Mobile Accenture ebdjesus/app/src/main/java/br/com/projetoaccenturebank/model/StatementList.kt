package br.com.projetoaccenturebank.model

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import java.io.Serializable
import java.lang.Exception
import com.google.gson.reflect.TypeToken



class StatementList : Serializable {
    var PREFS_STATEMENT: String = "prefs_statement"
    var PREFS_STATEMENT_DADOS: String = "prefs_statement_dados"


    var title : String? = null
    var desc : String? = null
    var date : String? = null
    var value : Float? = null

    /**
     * RETORAR LOGIN GRAVADO
     */

    fun retornar(context: Context): ArrayList<StatementList>? {
        val prefs = context.getSharedPreferences(PREFS_STATEMENT, Context.MODE_PRIVATE)

        val json = prefs.getString(PREFS_STATEMENT_DADOS, "")
        val listType = object : TypeToken<ArrayList<StatementList>>() {

        }.type

        val statementList = Gson().fromJson<ArrayList<StatementList>>(json, listType)

        return statementList
    }

    @Throws(Exception::class)
    fun iniciar(context: Context, lista: ArrayList<StatementList>) {
        val prefs = context.getSharedPreferences(PREFS_STATEMENT, Context.MODE_PRIVATE)
        val json = prefs.getString(PREFS_STATEMENT_DADOS, "")

        val listType = object : TypeToken<ArrayList<StatementList>>() {

        }.type
        val statementList = Gson().fromJson<ArrayList<StatementList>>(json, listType)

        if (statementList != null && statementList.size > 0)
            prefs.edit().clear().apply()

        val editor = prefs.edit()
        editor.putString(PREFS_STATEMENT_DADOS, Gson().toJson(lista))
        editor.apply()

    }

    fun limpar(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_STATEMENT, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }



}
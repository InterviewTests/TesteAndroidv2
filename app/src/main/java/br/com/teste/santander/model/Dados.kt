package br.com.teste.santander.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.Exception

class Dados : Serializable{
    var PREFS_DADOS: String = "prefs_dados"
    var PREFS_BUSCA_DADOS: String = "prefs_busca_dados"


    var title : String? = null
    var desc : String? = null
    var date : String? = null
    var value : Float? = null


    fun getDados(context: Context): ArrayList<Dados>? {
        val prefs = context.getSharedPreferences(PREFS_DADOS, Context.MODE_PRIVATE)

        val json = prefs.getString(PREFS_BUSCA_DADOS, "")
        val listType = object : TypeToken<ArrayList<Dados>>() {

        }.type

        return Gson().fromJson<ArrayList<Dados>>(json, listType)
    }

    @Throws(Exception::class)
    fun init(context: Context, lista: ArrayList<Dados>) {
        val prefs = context.getSharedPreferences(PREFS_DADOS, Context.MODE_PRIVATE)
        val json = prefs.getString(PREFS_BUSCA_DADOS, "")

        val listType = object : TypeToken<ArrayList<Dados>>() {

        }.type
        val dados = Gson().fromJson<ArrayList<Dados>>(json, listType)

        if (dados != null && dados.size > 0)
            prefs.edit().clear().apply()

        val editor = prefs.edit()
        editor.putString(PREFS_BUSCA_DADOS, Gson().toJson(lista))
        editor.apply()

    }

}
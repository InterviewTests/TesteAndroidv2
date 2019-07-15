package br.com.projetoaccenturebank.client.retrofit.task

import android.content.Context
import android.os.AsyncTask
import br.com.projetoaccenturebank.client.retrofit.OnCallback
import br.com.projetoaccenturebank.login.R
import br.com.projetoaccenturebank.model.Login
import br.com.projetoaccenturebank.model.StatementList
import br.com.projetoaccenturebank.util.APIError
import br.com.projetoaccenturebank.util.Loading
import br.com.projetoaccenturebank.util.Utils
import br.com.spotpromo.tigre_promotor.util.webclient.retrofit.Conexao
import java.lang.Exception

class TaskStatement
constructor(context: Context) : AsyncTask<Void, Void, Boolean>() {

    var callback : OnCallback
    var mensagem: String = ""
    val context: Context

    init {
        callback = context as OnCallback
        this.context = context
    }

    override fun onPreExecute() {
        super.onPreExecute()
        //Loading.hide()
        //Loading.show(context, "Atualizando as informações, aguarde!!!")

    }

    override fun doInBackground(vararg params: Void?): Boolean {
        try {

            val login = Login().retornar(context)!!
            val url = String.format(context.resources.getString(R.string.url_statement), login.userId)
            var request = Conexao().get(
                url, context
            )

            val response = request.execute()

            if (!response.isSuccessful)
                throw RuntimeException(APIError.getError(response.code(), context))

            val statementList = response.body()!!.statementList

            if(statementList!!.size > 0) {
                StatementList().iniciar(context, statementList)
                return true
            }

        } catch (err: Exception) {
            mensagem = err.message!!
        }

        return false
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        //Loading.hide()
        callback.onRetorno(result!!, mensagem)
    }

    override fun onCancelled() {
        super.onCancelled()
        //Loading.hide()
    }

    override fun onCancelled(result: Boolean?) {
        super.onCancelled(result)
        //Loading.hide()
    }
}
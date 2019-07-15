package br.com.teste.santander.tasks

import android.content.Context
import android.os.AsyncTask
import br.com.teste.santander.R
import br.com.teste.santander.conexao.Conexao
import br.com.teste.santander.conexao.OnCallback
import br.com.teste.santander.conexao.erro.APIError
import br.com.teste.santander.model.Dados
import br.com.teste.santander.model.Login
import java.lang.Exception

class TaskCarregaDados
constructor(context: Context) : AsyncTask<Void, Void, Boolean>() {

    var callback : OnCallback = context as OnCallback
    var mensagem: String = ""
    val context: Context

    init {
        this.context = context
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        try {

            val login = Login().returnLogin(context)!!
            val url = String.format(context.resources.getString(R.string.url_dados), login.userId)
            var request = Conexao().get(
                url, context
            )

            val response = request.execute()

            if (!response.isSuccessful)
                throw RuntimeException(APIError.getError(response.code(), context))

            val dados = response.body()!!.statementList

            if(dados!!.size > 0) {
                Dados().init(context, dados)
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

}
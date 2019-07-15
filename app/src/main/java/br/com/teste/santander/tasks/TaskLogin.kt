package br.com.teste.santander.tasks

import android.content.Context
import android.os.AsyncTask
import br.com.teste.santander.R
import br.com.teste.santander.conexao.Conexao
import br.com.teste.santander.conexao.OnCallback
import br.com.teste.santander.conexao.erro.APIError
import br.com.teste.santander.model.Login
import br.com.teste.santander.principal.carregamento.Loading
import br.com.teste.santander.util.Util
import java.lang.Exception

class TaskLogin
constructor(context: Context) : AsyncTask<String, Void, Boolean>() {

    var callback : OnCallback
    var mensagem: String = ""
    val context: Context

    init {
        callback = context as OnCallback
        this.context = context
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Loading.hide()
        Loading.show(context, context.resources.getString(R.string.validando))

    }

    override fun doInBackground(vararg params: String?): Boolean {
        try {
            var request = Conexao().post(
                context.resources.getString(R.string.url_login),
                Util.retornaHashJson(
                    "=",
                    String.format("user=%s", params[0]),
                    String.format("password=%s", params[1])
                ), context
            )

            val response = request.execute()

            if (!response.isSuccessful)
                throw RuntimeException(APIError.getError(response.code(), context))

            val login = response.body()!!.login
            val errorLogin = response.body()!!.login_error

            if (errorLogin?.code != null)
                throw java.lang.RuntimeException(errorLogin.message)

            if (login?.userId != null)
                Login().init(context, login, false)

            return true

        } catch (err: Exception) {
            mensagem = err.message!!
        }

        return false
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        Loading.hide()
        callback.onRetorno(result!!, mensagem)
    }

    override fun onCancelled() {
        super.onCancelled()
        Loading.hide()
    }

    override fun onCancelled(result: Boolean?) {
        super.onCancelled(result)
        Loading.hide()
    }
}
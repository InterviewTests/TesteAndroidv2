package br.com.bankapp.data

import android.os.AsyncTask
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import br.com.bankapp.model.Login
import br.com.bankapp.model.Usuario
import br.com.bankapp.util.BankHelper
import br.com.bankapp.util.Constantes
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class LoginTask(private val delegate: LoginDelegate, private val context: Context): AsyncTask<Login, Login, LoginRes>() {


    private var dialog: ProgressDialog? = null

    override fun onPreExecute() {
        super.onPreExecute()

        this.dialog = ProgressDialog(context)
        this.dialog?.setMessage("Login")
        this.dialog?.setCancelable(false)
        this.dialog?.show()
    }

    override fun doInBackground(vararg params: Login?): LoginRes {
        val loginRes = LoginRes()
        val login = params[0] as Login

        if (BankHelper.verificarConexao(context)) {
            try {
                val retorno = BankHelper.request(
                    Constantes.URL_LOGIN,
                    Constantes.REQUEST_METHOD_POST,
                    null,
                    null,
                    createQueryStringForParameters(createMapLogin(login))
                )
                if (retorno != null) {
                    if (retorno.statusCode == HttpURLConnection.HTTP_OK) {
                        val json = JSONTokener(retorno.strRetorno).nextValue() as JSONObject
                        val tipoLogin = object : TypeToken<Login>() {}.type as Type

                        val usuario = Usuario()
                        usuario.userId = json.getJSONObject("userAccount").getInt("userId")
                        usuario.name = json.getJSONObject("userAccount").getString("name")
                        usuario.bankAccount = json.getJSONObject("userAccount").getString("bankAccount")
                        usuario.agency = json.getJSONObject("userAccount").getString("agency")
                        usuario.balance = json.getJSONObject("userAccount").getDouble("balance")

                        loginRes.usuario = usuario
                        loginRes.statusCode = retorno.statusCode
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            loginRes.statusCode = 99
            loginRes.mensagem = Constantes.MSG_ERRO_CONNECTION
        }

        return loginRes
    }

    override fun onPostExecute(result: LoginRes) {
        super.onPostExecute(result)
        delegate.onLoginResult(result)
        this.dialog?.dismiss()
    }

    private fun createMapLogin(login: Login): Map<String, String>{
        val map = HashMap<String, String>()
        map["user"] = login.uesrename.toString()
        map["password"] = login.password.toString()

        return map
    }

    companion object{
        private val PARAMETER_DELMITER = '&'
        private val PARAMETER_EQUALS_CHAR = '='
        fun createQueryStringForParameters(parameters: Map<String, String>): String{
            val parametersAsQueryString = StringBuffer()
            if (parameters != null){
                var firsParameter = true
                for (parameterName in parameters.keys){
                    if (!firsParameter){
                        parametersAsQueryString.append(PARAMETER_DELMITER)
                    }

                    parametersAsQueryString.append(parameterName)
                        .append(PARAMETER_EQUALS_CHAR)
                        .append(URLEncoder.encode(parameters[parameterName]))

                    firsParameter = false
                }
            }
            return parametersAsQueryString.toString()
        }
    }


}
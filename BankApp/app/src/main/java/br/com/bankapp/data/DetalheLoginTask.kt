package br.com.bankapp.data

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import br.com.bankapp.model.DetalheLogin
import br.com.bankapp.util.BankHelper
import br.com.bankapp.util.Constantes
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import org.json.JSONTokener
import java.lang.reflect.Type
import java.net.HttpURLConnection

class DetalheLoginTask(private val delegate: DetalheLoginDelegate, private val context: Context): AsyncTask<Int, DetalheLogin, DetalheLoginRes>() {

    private var dialog: ProgressDialog? = null

    override fun onPreExecute() {
        super.onPreExecute()

        this.dialog = ProgressDialog(context)
        this.dialog?.setMessage("Detalhes do Usuario")
        this.dialog?.setCancelable(false)
        this.dialog?.show()
    }
    override fun doInBackground(vararg params: Int?): DetalheLoginRes {
        val detalheLoginRes = DetalheLoginRes()
        val idUsuario = params[0] as Int

        if (BankHelper.verificarConexao(context)) {
            try {
                val retorno = BankHelper.request(
                    Constantes.URL_DET_LOGIN + idUsuario,
                    Constantes.REQUEST_METHOD_GET,
                    null,
                    null,
                    null
                )
                if (retorno != null) {
                    if (retorno.statusCode == HttpURLConnection.HTTP_OK) {
                        val json = JSONTokener(retorno.strRetorno).nextValue() as JSONObject
                        val tipoLogin = object : TypeToken<DetalheLogin>() {}.type as Type

                        var listDetalheLogin: ArrayList<DetalheLogin> = ArrayList()
                        for (i in 0 until json.getJSONArray("statementList").length()) {
                            val detalheLogin = DetalheLogin()
                            detalheLogin.title =
                                (json.getJSONArray("statementList")[i] as JSONObject).getString("title")
                            detalheLogin.desc = (json.getJSONArray("statementList")[i] as JSONObject).getString("desc")
                            detalheLogin.date = (json.getJSONArray("statementList")[i] as JSONObject).getString("date")
                            detalheLogin.value =
                                (json.getJSONArray("statementList")[i] as JSONObject).getDouble("value")
                            listDetalheLogin.add(detalheLogin)
                        }
                        detalheLoginRes.listDetalheLogin = listDetalheLogin
                        detalheLoginRes.statusCode = retorno.statusCode
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            detalheLoginRes.statusCode = 99
            detalheLoginRes.mensagem = Constantes.MSG_ERRO_CONNECTION
        }

        return detalheLoginRes
    }

    override fun onPostExecute(result: DetalheLoginRes) {
        super.onPostExecute(result)
        delegate.onDetalheLoginResult(result)
        this.dialog?.dismiss()
    }
}
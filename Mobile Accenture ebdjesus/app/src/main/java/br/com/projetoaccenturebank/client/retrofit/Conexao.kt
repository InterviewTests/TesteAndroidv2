package br.com.spotpromo.tigre_promotor.util.webclient.retrofit

import android.content.Context
import br.com.projetoaccenturebank.login.R
import br.com.projetoaccenturebank.model.DadosJson
import br.com.spotpromo.tigre_promotor.util.webclient.retrofit.interface_client.Mobile_Client
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call

class Conexao : Retrofit_Client() {

    fun get(url: String, context: Context): Call<DadosJson> {
        val dados = getClient(context.resources.getString(R.string.url), Mobile_Client::class.java) as Mobile_Client
        return dados.get(url)
    }

    fun post(url: String, data: HashMap<String, String>, context: Context): Call<DadosJson> {
        val dados = getClient(context.resources.getString(R.string.url), Mobile_Client::class.java) as Mobile_Client
        return dados.post(url, data)
    }

}
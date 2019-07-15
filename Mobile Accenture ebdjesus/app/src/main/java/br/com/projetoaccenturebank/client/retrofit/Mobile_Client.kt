package br.com.spotpromo.tigre_promotor.util.webclient.retrofit.interface_client

import br.com.projetoaccenturebank.model.DadosJson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface Mobile_Client {
    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap data: HashMap<String, String>): Call<DadosJson>

    @GET
    fun get(@Url url: String): Call<DadosJson>


}
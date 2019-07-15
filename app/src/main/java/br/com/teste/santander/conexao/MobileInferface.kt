package br.com.teste.santander.conexao

import br.com.teste.santander.model.Json
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface MobileInferface {
    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap data: HashMap<String, String>): Call<Json>

    @GET
    fun get(@Url url: String): Call<Json>


}
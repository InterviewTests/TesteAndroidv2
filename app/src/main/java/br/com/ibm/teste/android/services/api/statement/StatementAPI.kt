package br.com.ibm.teste.android.services.api.statement

import br.com.ibm.teste.android.services.models.StatementsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:28
 */
interface StatementAPI {

    /**
     * The API for retrieve all statements
     */
    @GET("statements/{id}")
    fun statements(@Path("id") id: Int): Call<StatementsResponse>
}
package br.com.crmm.bankapplication.framework.datasource.remote.abstraction

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.StatementListResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementService {

    @GET("statements/{userId}")
    fun fetch(
        @Path("userId") shoppingListId: String
    ): Call<StatementListResponseDTO>
}
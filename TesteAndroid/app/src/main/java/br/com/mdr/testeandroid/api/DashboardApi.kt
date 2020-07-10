package br.com.mdr.testeandroid.api

import br.com.mdr.testeandroid.model.api.DashboardApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */
interface DashboardApi {
    companion object {
        private const val USER_ID_PARAM = "id"
        private const val STATEMENTS_PATH = "statements/{$USER_ID_PARAM}"
    }

    @GET(STATEMENTS_PATH)
    suspend fun getStatements(@Path(USER_ID_PARAM) userId: String): Response<DashboardApiModel>
}
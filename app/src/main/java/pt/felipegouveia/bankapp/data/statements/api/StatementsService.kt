package pt.felipegouveia.bankapp.data.statements.api

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * StatementsService - Provides methods to interact with statements endpoints of BankApp API
 */
interface StatementsService {

    /**
     * Method to fetch the list of statements
     *
     * @return RxJava `Single` for the list of statements.
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("statements/{id}")
    fun getStatementsList(
        @Path("id") id: Int = 0
    ): Single<StatementsResponse>

}

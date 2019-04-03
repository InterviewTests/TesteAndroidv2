package br.com.rms.bankapp.data.remote.api

import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.data.remote.model.StatementResponse
import br.com.rms.bankapp.data.remote.model.UserResponse
import io.reactivex.Single
import retrofit2.http.*

interface BankAppApiService {

    @FormUrlEncoded
    @POST(ENDPOINT_LOGIN)
    fun login(@Field(KEY_USER) user: String,
              @Field(KEY_PASSWORD) password: String) : Single<UserResponse>

    @GET(ENDPOINT_STATEMENTS)
    fun getStatement(@Path(KEY_PAGE) page: Int) : Single<StatementResponse>


}
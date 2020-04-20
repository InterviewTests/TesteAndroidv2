package com.jfgjunior.bankapp.data.external

import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.data.models.User
import com.jfgjunior.bankapp.data.models.UserCredentials
import io.reactivex.Single
import retrofit2.http.*

interface BankAPI {

    @POST(PATH_LOGIN)
    fun loginUser(@Body userCredentials: UserCredentials): Single<ResponseWrapper<User>>

    @GET("$PATH_STATEMENTS/{$PATH_USER_ID}")
    fun getTransactions(@Path(PATH_USER_ID) userId: Int): Single<ResponseWrapper<List<Statement>>>

    companion object {
        internal const val BASE_URL = "https://bank-app-test.herokuapp.com/"

        private const val PATH_LOGIN = "api/login"
        private const val PATH_STATEMENTS = "api/statements"
        private const val PATH_USER_ID= "userId"
    }
}


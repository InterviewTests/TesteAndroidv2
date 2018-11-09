package br.com.santander.android.bank.repository.api

import br.com.santander.android.bank.repository.model.Login
import br.com.santander.android.bank.repository.model.Statements
import br.com.santander.android.bank.repository.model.UserAccount
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

interface BankApiService {

    @POST("/api/login")
    fun login(@Body login: Login): Observable<UserAccount>

    @GET("/api/statements/{userId}")
    fun statements(@Path("userId") userId: Int): Observable<Statements>
}
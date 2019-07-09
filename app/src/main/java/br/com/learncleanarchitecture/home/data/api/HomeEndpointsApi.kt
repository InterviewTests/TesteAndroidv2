package br.com.learncleanarchitecture.home.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface HomeEndpointsApi {
    @GET("statements/{id}")
    fun getStatements(@Path("id") id: Int): Observable<HomeData.HomeResult>
}
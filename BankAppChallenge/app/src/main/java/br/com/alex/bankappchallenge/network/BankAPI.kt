package br.com.alex.bankappchallenge.network

import br.com.alex.bankappchallenge.network.model.LoginRequest
import br.com.alex.bankappchallenge.network.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BankAPI {

    @POST
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}
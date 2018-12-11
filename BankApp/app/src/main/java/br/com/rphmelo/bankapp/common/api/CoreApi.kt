package br.com.rphmelo.bankapp.common.api

import br.com.rphmelo.bankapp.common.utils.Variables.ROOT_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoreApi {

    fun retrofit() = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
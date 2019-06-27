package com.br.rafael.TesteAndroidv2.data.retrofit

import com.br.rafael.TesteAndroidv2.Util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {

    companion object {
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}
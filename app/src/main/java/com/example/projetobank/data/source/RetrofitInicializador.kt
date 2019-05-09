package com.example.projetobank.data.source

import com.example.projetobank.data.source.remote.DadosBancarioService
import com.example.projetobank.data.source.remote.UsuarioService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInicializador {
    companion object {
        var url: String? = null
    }

    lateinit var retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.readTimeout(120, TimeUnit.SECONDS)
        client.connectTimeout(120, TimeUnit.SECONDS)
        client.addInterceptor(interceptor)

        url?.let {
            retrofit = Retrofit.Builder()
                .baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
    }

    fun dadosBancarioService() = retrofit.create(DadosBancarioService::class.java)

    fun usuarioService() = retrofit.create(UsuarioService::class.java)

}
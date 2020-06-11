package com.tech.fit.diet_plan.activity.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import projects.kevin.bankapp.user.service.ApiUserService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by caporal on 23/07/18.
 */


class APIClient {

    companion object {

        private var protocol: ApiUserService? = null

        fun getReactiveService(): ApiUserService? {
            if (protocol == null){

                protocol = try {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://bank-app-test.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()

                    retrofit.create(ApiUserService::class.java)
                }catch (e: IllegalArgumentException){
                    null
                }

            }
            return protocol
        }


    }
}
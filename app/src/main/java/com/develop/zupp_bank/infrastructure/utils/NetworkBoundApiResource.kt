package com.develop.zupp_bank.infrastructure.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

abstract class NetworkBoundApiResource<RequestType> protected constructor(private val callback: ResourceCallback<Resource<RequestType>>) {

    init {
        callApiNet()
    }

    private fun callApiNet() {
        createCall().enqueue(object : Callback<ReturnAPI<RequestType>> {

            override fun onResponse(call: Call<ReturnAPI<RequestType>>, response: Response<ReturnAPI<RequestType>>) {

                if (response.isSuccessful && response.body()!!.user != null) {

                    callback.onComplete(Resource.success(handleDataSuccess(response.body()!!.user)))

                } else {
                    callback.onComplete(Resource.error(""))
                }

                Log.e("TAG", response.body()!!.user.toString())

            }

            override fun onFailure(call: Call<ReturnAPI<RequestType>>, t: Throwable) {
                callback.onComplete(Resource.failure(t))
            }
        })
    }

    protected abstract fun createCall(): Call<ReturnAPI<RequestType>>
    protected abstract fun handleDataSuccess(resultType: RequestType): RequestType

}
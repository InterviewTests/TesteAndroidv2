package com.develop.tcs_bank.infrastructure.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NetworkBoundApiStatement<RequestType> protected constructor(private val callback: ResourceCallback<Resource<RequestType>>) {

    init {
        callApiNet()
    }

    private fun callApiNet() {
        createCall().enqueue(object : Callback<ReturnStatement<RequestType>> {

            override fun onResponse(call: Call<ReturnStatement<RequestType>>, response: Response<ReturnStatement<RequestType>>) {

                if (response.isSuccessful && response.body()!!.statement != null) {

                    callback.onComplete(Resource.success(handleDataSuccess(response.body()!!.statement)))

                } else {
                    callback.onComplete(Resource.error(""))
                }

                Log.e("TAG", response.body()!!.statement.toString())

            }

            override fun onFailure(call: Call<ReturnStatement<RequestType>>, t: Throwable) {
                callback.onComplete(Resource.failure(t))
            }
        })
    }

    protected abstract fun createCall(): Call<ReturnStatement<RequestType>>
    protected abstract fun handleDataSuccess(resultType: RequestType): RequestType

}
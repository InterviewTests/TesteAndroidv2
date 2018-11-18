package com.rafaelpereiraramos.testeandroidv2.api

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
class LiveDataCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, LiveData<ResponseWrapper<R>>> {

    override fun adapt(call: Call<R>): LiveData<ResponseWrapper<R>> {
        return object : LiveData<ResponseWrapper<R>>() {
            val started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()

                if (started.compareAndSet(false, true)) {

                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ResponseWrapper.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(ResponseWrapper.create(throwable))
                        }
                    })
                }
            }
        }
    }

    override fun responseType(): Type = responseType
}
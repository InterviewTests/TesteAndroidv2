package com.rafaelpereiraramos.testeandroidv2.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
class LiveDataCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, LiveData<R>> {

    override fun adapt(call: Call<R>): LiveData<R> {
        return object : LiveData<R>() {
            val started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()

                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R>{
                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(null)
                        }

                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.body())
                        }
                    })
                }
            }
        }
    }

    override fun responseType(): Type = responseType
}
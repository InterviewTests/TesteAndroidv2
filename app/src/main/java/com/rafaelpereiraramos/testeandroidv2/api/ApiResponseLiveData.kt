package com.rafaelpereiraramos.testeandroidv2.api

import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
class ApiResponseLiveData<T>(
    private val call: Call<T>
) : MutableLiveData<T>() {

    private val started = AtomicBoolean(false)

    var code = -1
    private set

    var message = ""
    private set

    var isSuccessful: Boolean = code in 200..299
    private set

    var errorBody: ResponseBody? = null

    override fun onActive() {
        if (started.compareAndSet(false, true)) {

            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    code = response.code()
                    message = response.message()
                    isSuccessful = response.isSuccessful
                    errorBody = response.errorBody()
                    postValue(response.body())
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    message = throwable.message ?: "unknown error"
                    postValue(null)
                    Timber.tag("API").e(throwable)
                }
            })
        }
    }
}
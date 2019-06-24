package br.com.vinicius.bankapp

import br.com.vinicius.bankapp.internal.MOCK_FAILED
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockCall<T>(private val responseCase: ResponseCase, val responseData: T): Call<T> {

    enum class ResponseCase {
        SUCCESS, FAILURE
    }


    override fun enqueue(callback: Callback<T>) {
       if(responseCase == ResponseCase.SUCCESS)
           callback.onResponse(this, Response.success(responseData))
        else
           callback.onFailure(this, Throwable(MOCK_FAILED))
    }

    override fun isExecuted(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): Call<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCanceled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(): Response<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun request(): Request {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
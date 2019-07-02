package io.github.maikotrindade.bankapp.statement.domain

import io.github.maikotrindade.bankapp.base.network.BaseNetwork
import io.github.maikotrindade.bankapp.statement.model.StatementsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.ref.WeakReference

interface StatementsWorkerInput {
    fun getStatements(userId: Int)
}

class StatementsWorker(val output: WeakReference<StatementsInteractor>) : StatementsWorkerInput {

    override fun getStatements(userId : Int) {
        val statementsResponseCall = BaseNetwork.get(StatementsInterface::class.java).getStatements(userId)
        statementsResponseCall.enqueue(object : Callback<StatementsResponse> {

            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                if (response.isSuccessful) {
                    output.get()?.onRequestStatementsSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                output.get()?.onRequestStatementsError()
            }

        })
    }

}

interface StatementsInterface {

    @GET("statements/{userId}")
    fun getStatements(@Path("userId") userId: Int): Call<StatementsResponse>

}
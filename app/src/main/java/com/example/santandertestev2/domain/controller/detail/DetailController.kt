package com.example.santandertestev2.domain.controller.detail

import android.util.Log
import com.example.santandertestev2.data.AccountService
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.data.Rest
import com.example.santandertestev2.domain.model.controller.Invoice
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.view.detail.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailController(private val detailActivity: DetailActivity) {

    private val preferenceData : PreferenceData

    init {
        this.preferenceData = PreferenceData(this.detailActivity)
    }

    fun getUser(): UserAccount {
        return detailActivity.intent.extras?.getSerializable("user") as UserAccount
    }

    fun getUserInvoice(){
        val account = Rest.getRetrofitInstance()?.create(AccountService::class.java)
        val res = account?.fetchInvoice(1)
        res?.enqueue(object : Callback<Invoice> {
            override fun onFailure(call: Call<Invoice>, t: Throwable) {
                Log.e("ERRO", t.message)
            }

            override fun onResponse(
                call: Call<Invoice>,
                response: Response<Invoice>
            ) {
                val list = response.body()?.getInvoice()
                list?.let {
                    detailActivity.onInvoiceLoad(it)
                }
            }
        })
    }

    fun logout(){
        this.preferenceData.clearData()
        detailActivity.finish()
    }


}
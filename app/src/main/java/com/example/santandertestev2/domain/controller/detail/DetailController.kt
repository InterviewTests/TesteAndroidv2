package com.example.santandertestev2.domain.controller.detail

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.santandertestev2.data.AccountService
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.data.Rest
import com.example.santandertestev2.domain.model.controller.Invoice
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.domain.presenter.DetailPresenter
import com.example.santandertestev2.domain.presenter.IDetailPresenter
import com.example.santandertestev2.view.detail.DetailActivity
import com.example.santandertestev2.view.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailController(private val context: Context, private val presenter : DetailPresenter) {

    private val preferenceData : PreferenceData

    init {
        this.preferenceData = PreferenceData(context)
    }

    fun getUser(intent: Intent) {
        val user = intent.extras?.getSerializable("user") as UserAccount
        presenter.setHeader(user)
    }

    fun getUserInvoice(){
        val account = Rest.getRetrofitInstance()?.create(AccountService::class.java)
        val res = account?.fetchInvoice(1)
        res?.enqueue(object : Callback<Invoice> {
            override fun onFailure(call: Call<Invoice>, t: Throwable) {
                presenter.errorOnFetchInvoice("Houve um erro. Clique em atualizar página")
            }

            override fun onResponse(call: Call<Invoice>,response: Response<Invoice>) {
                val list = response.body()?.getInvoice()
                list?.let {
                    presenter.showInvoice(it)
                }
            }
        })
    }

    fun logout(){
        this.preferenceData.clearData()
        val intent = Intent(context, LoginActivity::class.java)
        presenter.logout(intent)
    }


}
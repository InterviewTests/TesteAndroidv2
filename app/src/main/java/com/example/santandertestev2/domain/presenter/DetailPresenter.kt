package com.example.santandertestev2.domain.presenter

import android.content.Context
import android.content.Intent
import com.example.santandertestev2.domain.model.controller.InvoiceItem
import com.example.santandertestev2.domain.model.controller.UserAccount

class DetailPresenter(private val activity: IDetailPresenter) {

    fun errorOnFetchInvoice(msg : String){
        activity.onFetchInvoiceError(msg)
    }

    fun showInvoice(list : List<InvoiceItem>){
        activity.onInvoiceLoad(list)
    }

    fun logout(intent: Intent){
        activity.onLogout(intent)
    }

    fun setHeader(userAccount: UserAccount){
        activity.onLoadUser(userAccount)
    }

}
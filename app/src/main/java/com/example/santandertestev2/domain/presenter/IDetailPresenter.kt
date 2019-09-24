package com.example.santandertestev2.domain.presenter

import android.content.Intent
import com.example.santandertestev2.domain.model.controller.InvoiceItem
import com.example.santandertestev2.domain.model.controller.UserAccount

interface IDetailPresenter {

    fun onInvoiceLoad(itens : List<InvoiceItem>)
    fun onLogout(intent: Intent)
    fun onLoadUser(userAccount: UserAccount)
    fun onFetchInvoiceError(msg : String)

}
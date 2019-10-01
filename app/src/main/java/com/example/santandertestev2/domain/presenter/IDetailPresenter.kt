package com.example.santandertestev2.domain.presenter

import android.content.Intent
import com.example.santandertestev2.domain.model.InvoiceItem
import com.example.santandertestev2.domain.model.UserAccount

interface IDetailPresenter {

    fun onInvoiceLoad(itens : List<InvoiceItem>)
    fun onLogout(intent: Intent)
    fun onLoadUser(userAccount: UserAccount)
    fun onFetchInvoiceError(msg : String)

}
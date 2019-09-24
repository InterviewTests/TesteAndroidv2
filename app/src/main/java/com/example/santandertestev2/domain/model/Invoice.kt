package com.example.santandertestev2.domain.model.controller

import com.google.gson.annotations.SerializedName

class Invoice {
    @SerializedName("statementList")
    private var invoiceItemList : List<InvoiceItem>?=null

    fun getInvoice(): List<InvoiceItem>? {
        return this.invoiceItemList
    }
}
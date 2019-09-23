package com.example.santandertestev2.domain.controller.detail

import com.example.santandertestev2.domain.model.controller.InvoiceItem

interface IDetail {
    fun onInvoiceLoad(itens : List<InvoiceItem>)
    //fun onInvoiceLoad()
}
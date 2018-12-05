package com.br.natanfelipe.bankapp.view.home

import android.content.Context
import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.model.Statement
import com.br.natanfelipe.bankapp.view.adapter.ItemBankAdapter

class HomeModel {
}

class HomeViewModel {
    var billsList = mutableListOf<Statement>()
}

class HomeRequest() {
    var api = RestApi()
}

class HomeResponse() {
    var billsList = mutableListOf<Statement>()
}
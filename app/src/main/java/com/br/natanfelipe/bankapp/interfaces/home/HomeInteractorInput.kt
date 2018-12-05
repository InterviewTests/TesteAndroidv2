package com.br.natanfelipe.bankapp.interfaces.home

import com.br.natanfelipe.bankapp.view.home.HomeRequest

interface HomeInteractorInput {
    fun fetchHomeMetaData(homeRequest : HomeRequest)
}
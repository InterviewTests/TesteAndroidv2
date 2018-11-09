package br.com.santander.android.bank

import br.com.santander.android.bank.repository.api.BankApiFactory

object Injection {

    val bankApiService by lazy { BankApiFactory.create() }
}
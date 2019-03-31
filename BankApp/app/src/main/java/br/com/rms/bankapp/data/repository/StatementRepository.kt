package br.com.rms.bankapp.data.repository

import br.com.rms.bankapp.data.local.database.dao.StatementDao
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import javax.inject.Singleton

@Singleton
class StatementRepository(
    private val statementDao: StatementDao,
    private val apiService: BankAppApiService
) {

}
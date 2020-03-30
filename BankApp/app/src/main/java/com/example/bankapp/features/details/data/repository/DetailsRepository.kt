package com.example.bankapp.features.details.data.repository

import com.example.bankapp.features.details.data.service.StatementService
import com.example.bankapp.features.details.data.service.Statements
import io.reactivex.Single
import javax.inject.Inject

interface DetailsRepository {
    fun getStatementDetails(id: String): Single<Statements?>

    class Impl @Inject constructor(private val service: StatementService) : DetailsRepository {
        override fun getStatementDetails(id: String): Single<Statements?> {
            return service.getStatementService(id)
        }
    }
}
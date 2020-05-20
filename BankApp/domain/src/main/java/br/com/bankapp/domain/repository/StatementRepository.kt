package br.com.bankapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.bankapp.domain.models.Statement

interface StatementRepository {

    suspend fun loadStatements(userId: Int)

    fun getStatements(userId: Int): LiveData<PagedList<Statement>>
}
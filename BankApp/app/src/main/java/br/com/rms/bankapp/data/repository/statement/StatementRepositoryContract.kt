package br.com.rms.bankapp.data.repository.statement

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.data.remote.model.StatementResponse
import io.reactivex.Single

interface StatementRepositoryContract {
    fun loadRemoteStatement(nextPage: Int): Single<StatementResponse>


}

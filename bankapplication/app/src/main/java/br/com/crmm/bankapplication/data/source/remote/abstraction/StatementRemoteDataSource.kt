package br.com.crmm.bankapplication.data.source.remote.abstraction

import br.com.crmm.bankapplication.data.state.StatementDataState
import io.reactivex.rxjava3.core.Flowable

interface StatementRemoteDataSource {

    fun fetch(userId: String): Flowable<StatementDataState>

}
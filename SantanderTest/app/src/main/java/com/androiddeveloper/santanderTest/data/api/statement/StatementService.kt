package com.androiddeveloper.santanderTest.data.api.statement

import com.androiddeveloper.santanderTest.data.model.statements.Statements
import com.androiddeveloper.santanderTest.shared.network.Factory
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object StatementService {

    fun requestStatement(id: Int): Flowable<Response<Statements>> {
        return Factory.buildApi(StatementEndpoint::class.java)
            .getStatements(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestMockStatement(id: Int): Flowable<Response<Statements>> {
        return Factory.buildMockApi(StatementEndpoint::class.java)
            .getStatements(id)
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
    }
}
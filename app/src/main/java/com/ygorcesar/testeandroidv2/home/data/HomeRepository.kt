package com.ygorcesar.testeandroidv2.home.data

import com.ygorcesar.testeandroidv2.base.common.network.NetworkHandler
import com.ygorcesar.testeandroidv2.base.data.BaseRemoteRepository
import com.ygorcesar.testeandroidv2.home.model.Statement
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {

    fun getStatements(userId: Int): Single<List<Statement>>

    class Remote
    @Inject constructor(
        private val statementMapper: StatementMapper,
        private val homeService: HomeService,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), HomeRepository {

        override fun getStatements(userId: Int): Single<List<Statement>> =
            request(statementMapper) {
                homeService.getStatements(userId)
            }
    }
}
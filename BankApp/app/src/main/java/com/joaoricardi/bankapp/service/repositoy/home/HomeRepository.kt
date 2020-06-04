package com.joaoricardi.bankapp.service.repositoy.home

import com.joaoricardi.bankapp.models.home.StatementResponse
import com.joaoricardi.bankapp.service.RetrofitService
import com.joaoricardi.bankapp.service.api.home.HomeApi
import kotlinx.coroutines.Deferred

class HomeRepository: HomeRepositoryContarct {

    val homeApi: HomeApi = RetrofitService().getHomeApi()

    override fun getStateMent(): Deferred<StatementResponse> {
        return homeApi.getStatements()
    }

}
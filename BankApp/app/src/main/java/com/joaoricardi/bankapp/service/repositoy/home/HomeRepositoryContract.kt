package com.joaoricardi.bankapp.service.repositoy.home

import com.joaoricardi.bankapp.models.home.StatementResponse
import kotlinx.coroutines.Deferred

interface HomeRepositoryContarct {

    fun getStateMent(): Deferred<StatementResponse>
}
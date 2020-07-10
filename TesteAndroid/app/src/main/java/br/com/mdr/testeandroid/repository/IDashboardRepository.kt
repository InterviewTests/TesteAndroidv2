package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.model.api.DashboardApiModel

interface IDashboardRepository {
    suspend fun getStatements(userId: Int): DashboardApiModel?
}
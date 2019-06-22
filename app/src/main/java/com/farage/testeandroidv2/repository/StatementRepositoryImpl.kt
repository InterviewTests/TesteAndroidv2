package com.farage.testeandroidv2.repository

import com.farage.testeandroidv2.datasource.StatementDataSource
import com.farage.testeandroidv2.domain.StatementRepository
import com.farage.testeandroidv2.domain.model.StatementsModel
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.dateFormat
import com.farage.testeandroidv2.util.toMoney

class StatementRepositoryImpl(private val statementDataSource: StatementDataSource) : StatementRepository {

    override suspend fun getAllStatements(id: Int): ResultState<List<StatementsModel>> {

        var statementsList = mutableListOf<StatementsModel>()

        statementDataSource.getAllStatements(id).let {
            it?.data!!.statementList.map {
                statementsList.add(
                    StatementsModel(
                        title = it.title,
                        desc = it.desc,
                        date = it.date.dateFormat(),
                        value = it.value.toString().toMoney()
                    )
                )
            }
        }
        return ResultState.success(statementsList)
    }

}
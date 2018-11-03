package com.ygorcesar.testeandroidv2.home.data

import com.ygorcesar.testeandroidv2.base.data.BaseMapper
import com.ygorcesar.testeandroidv2.home.model.Statement
import timber.log.Timber
import javax.inject.Inject

class StatementMapper @Inject constructor() : BaseMapper<StatementsResponse, List<Statement>>() {
    override val trackException: (Throwable) -> Unit = { Timber.i("Send error to crashlytics") }

    override fun assertEssentialParams(raw: StatementsResponse) {
        val statementsRaw = raw.data ?: listOf()

        statementsRaw.forEach { statementRaw ->
            if (statementRaw.title.isNullOrBlank()) addMissingParam("title")
            if (statementRaw.desc.isNullOrBlank()) addMissingParam("desc")
            if (statementRaw.date.isNullOrBlank()) addMissingParam("date")
            if (statementRaw.value == null) addMissingParam("value")
        }

        if (isMissingParams()) throwException(raw)
    }

    override fun copyValues(raw: StatementsResponse): List<Statement> {
        val statements = mutableListOf<Statement>()
        raw.data!!.forEach { statementRaw ->
            statements.add(
                Statement(
                    title = statementRaw.title!!,
                    desc = statementRaw.desc!!,
                    date = statementRaw.date!!,
                    value = statementRaw.value!!.toBigDecimal()
                )
            )
        }
        return statements.toList()
    }

}
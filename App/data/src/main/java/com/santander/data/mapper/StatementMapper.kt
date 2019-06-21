package com.santander.data.mapper

import com.santander.data.mapper.base.BaseMapper
import com.santander.data.source.remote.entity.response.StatementResponse
import com.santander.domain.entity.business.Money
import com.santander.domain.entity.business.Statement


class StatementMapper : BaseMapper<StatementResponse, Statement> {

    override fun toEntity(from: StatementResponse): Statement {
        return Statement(
            date = from.date.orEmpty(),
            title = from.title.orEmpty(),
            value = Money(value = from.value ?: 0.0),
            desc = from.desc.orEmpty()
        )
    }

    fun toEntity(from: List<StatementResponse>): List<Statement> {
        return from.map { toEntity(it) }
    }

}
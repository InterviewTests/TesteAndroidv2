package com.jeanjnap.data.di

import com.jeanjnap.data.mapper.Mapper
import com.jeanjnap.data.mapper.StatementSummaryResponseToStatementListMapper
import com.jeanjnap.data.mapper.UserDataResponseToUserAccountMapper
import com.jeanjnap.data.model.response.StatementSummaryResponse
import com.jeanjnap.data.model.response.UserDataResponse
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.UserAccount
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {
    val mapperModulesItems = module {
        single<Mapper<UserDataResponse, UserAccount>>(
            named(UserDataResponseToUserAccountMapper::class.java.name)
        ) { UserDataResponseToUserAccountMapper() }

        single<Mapper<StatementSummaryResponse, List<Statement>>>(
            named(StatementSummaryResponseToStatementListMapper::class.java.name)
        ) { StatementSummaryResponseToStatementListMapper() }
    }
}
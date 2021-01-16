package com.jeanjnap.data.di

import com.jeanjnap.data.mapper.Mapper
import com.jeanjnap.data.mapper.UserDataResponseToUserAccountMapper
import com.jeanjnap.data.model.response.UserDataResponse
import com.jeanjnap.domain.entity.UserAccount
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {
    val mapperModulesItems = module {
        single<Mapper<UserDataResponse, UserAccount>>(
            named(UserDataResponseToUserAccountMapper::javaClass.name)
        ) {
            UserDataResponseToUserAccountMapper()
        }
    }
}
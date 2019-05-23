package com.example.testesantander.plataform.di

import com.example.testesantander.data.RetrofitConfig
import com.example.testesantander.domain.usecase.GetStatementsUseCase
import com.example.testesantander.domain.usecase.GetUserUseCase
import com.example.testesantander.domain.usecase.IGetStatementsUseCase
import com.example.testesantander.domain.usecase.IGetUserUseCase
import org.koin.dsl.module.module

val useCaseModule = module {
    factory { GetUserUseCase(RetrofitConfig().service()) as IGetUserUseCase}
    factory { GetStatementsUseCase(RetrofitConfig().service()) as IGetStatementsUseCase }
}
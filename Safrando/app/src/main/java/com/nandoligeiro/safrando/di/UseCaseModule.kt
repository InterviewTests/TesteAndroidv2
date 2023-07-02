package com.nandoligeiro.safrando.di

import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCase
import com.nandoligeiro.safrando.domain.common.checkCPF.NewCheckCPFUseCaseImpl
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCase
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCaseImpl
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterBrUseCaseImpl
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterUseCase
import com.nandoligeiro.safrando.domain.common.dateFormatter.DateFormatterUseCase
import com.nandoligeiro.safrando.domain.common.dateFormatter.DateFormatterUseCaseImpl
import com.nandoligeiro.safrando.domain.login.usecase.checkLogin.CheckLoginUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkLogin.CheckLoginUseCaseImpl
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCaseImpl
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCase
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCaseImpl
import com.nandoligeiro.safrando.domain.statements.usecase.GetBankStatementUseCase
import com.nandoligeiro.safrando.domain.statements.usecase.GetBankStatementUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {


    @Binds
    fun bindCheckPasswordUseCase(
        useCase: CheckPasswordUseCaseImpl
    ): CheckPasswordUseCase

    @Binds
    fun bindCheckEmailUseCase(
        useCase: CheckEmailUseCaseImpl
    ): CheckEmailUseCase

    @Binds
    fun bindCheckCPFUseCaseUseCase(
        useCase: NewCheckCPFUseCaseImpl
    ): CheckCPFUseCase

    @Binds
    fun bindDateFormatterUseCaseUseCase(
        useCase: DateFormatterUseCaseImpl
    ): DateFormatterUseCase

    @Binds
    fun bindFormatCurrencyUseCaseUseCase(
        useCase: CurrencyFormatterBrUseCaseImpl
    ): CurrencyFormatterUseCase

    @Binds
    fun bindGetBankStatementUseCase(
        useCase: GetBankStatementUseCaseImpl
    ): GetBankStatementUseCase

    @Binds
    fun bindCheckLoginUseCase(
        useCase: CheckLoginUseCaseImpl
    ): CheckLoginUseCase

    @Binds
    fun bindPostLoginUseCase(
        useCase: PostLoginUseCaseImpl
    ): PostLoginUseCase
}

package com.santander.domain.usecase.impl

import com.santander.domain.repository.IAccountRepository
import com.santander.domain.usecase.ICleanAccountUseCase
import io.reactivex.Completable

class CleanAccountUseCase(private val accountRepository: IAccountRepository): ICleanAccountUseCase {

    override fun execute(): Completable {
        return accountRepository.clean()
    }
}
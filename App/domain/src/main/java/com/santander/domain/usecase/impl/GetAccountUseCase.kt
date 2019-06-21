package com.santander.domain.usecase.impl

import com.santander.domain.entity.business.UserAccount
import com.santander.domain.repository.IAccountRepository
import com.santander.domain.usecase.IGetAccountUseCase
import io.reactivex.Observable

class GetAccountUseCase(private val accountRepository: IAccountRepository): IGetAccountUseCase {

    override fun execute(): Observable<UserAccount> {
        return accountRepository.get()
    }
}
package com.zuptest.domain.business.usecase.impl

import com.zuptest.domain.business.model.Account
import com.zuptest.domain.business.model.Credentials
import com.zuptest.domain.business.usecase.DoLoginUseCase
import com.zuptest.domain.repository.LoginRepository
import io.reactivex.Observable

class DoLoginUseCaseImpl(private val repository: LoginRepository) : DoLoginUseCase {
    override fun execute(params: Credentials): Observable<Account> {
        return repository.doLogin(params)
    }
}
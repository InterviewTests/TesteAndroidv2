package com.zuptest.santander.domain.business.usecase.impl

import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.business.usecase.DoLoginUseCase
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Observable

class DoLoginUseCaseImpl(private val repository: LoginRepository) : DoLoginUseCase {
    override fun execute(params: Credentials): Observable<Account> {
        return repository.doLogin(params)
    }
}
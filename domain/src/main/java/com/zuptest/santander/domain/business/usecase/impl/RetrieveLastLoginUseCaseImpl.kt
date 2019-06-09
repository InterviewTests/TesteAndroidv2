package com.zuptest.santander.domain.business.usecase.impl

import com.zuptest.santander.domain.business.usecase.RetrieveLastLoginUseCase
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Observable

class RetrieveLastLoginUseCaseImpl(
    private val repository: LoginRepository
) : RetrieveLastLoginUseCase {
    override fun execute(): Observable<String> {
        return repository.getLastLogin()
    }
}
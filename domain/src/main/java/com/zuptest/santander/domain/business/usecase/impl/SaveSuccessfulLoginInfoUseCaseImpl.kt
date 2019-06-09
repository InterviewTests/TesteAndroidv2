package com.zuptest.santander.domain.business.usecase.impl

import com.zuptest.santander.domain.business.usecase.SaveSuccessfulLoginInfoUseCase
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Completable

class SaveSuccessfulLoginInfoUseCaseImpl(
    private val repository: LoginRepository
) : SaveSuccessfulLoginInfoUseCase {

    override fun execute(params: String): Completable {
        return repository.saveLogin(params)
    }
}
package com.santander.domain.usecase.impl

import com.santander.domain.entity.input.SessionQuery
import com.santander.domain.repository.IAccountRepository
import com.santander.domain.repository.ILoginRepository
import com.santander.domain.usecase.ILoginUseCase
import io.reactivex.Completable

class LoginUseCaseImpl(
    private val loginRepository: ILoginRepository,
    private val accountRepository: IAccountRepository
) : ILoginUseCase {

    override fun execute(params: SessionQuery.SignIn): Completable {
        return loginRepository.login(params)
//            .doOnNext { loginRepository.saveUser(params.user) }
            .flatMapCompletable {
                accountRepository.save(it).andThen(loginRepository.saveUser(params.user))
            }
    }
}
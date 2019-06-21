package com.santander.domain.usecase.impl

import com.santander.domain.repository.ILoginRepository
import com.santander.domain.usecase.IGetUserUseCase
import io.reactivex.Observable

class GetUserUseCaseImpl(
    private val loginRepository: ILoginRepository
) : IGetUserUseCase {

    override fun execute(): Observable<String> {
        return loginRepository.getUser()
    }
}
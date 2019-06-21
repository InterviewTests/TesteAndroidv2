package com.santander.domain.usecase

import com.santander.domain.entity.input.SessionQuery
import com.santander.domain.usecase.core.UseCase

interface ILoginUseCase: UseCase.FromCompletable.WithParameter<SessionQuery.SignIn>
package com.santander.domain.usecase

import com.santander.domain.entity.business.UserAccount
import com.santander.domain.usecase.core.UseCase

interface IGetAccountUseCase: UseCase.FromObservable.WithoutParameter<UserAccount>
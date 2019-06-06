package com.zuptest.domain.business.usecase

import com.zuptest.domain.business.model.Account
import com.zuptest.domain.business.model.Credentials

interface DoLoginUseCase : UseCase.WithParameter<Credentials, Account>
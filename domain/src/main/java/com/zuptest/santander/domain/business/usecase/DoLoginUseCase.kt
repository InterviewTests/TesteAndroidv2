package com.zuptest.santander.domain.business.usecase

import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Credentials

interface DoLoginUseCase : UseCase.WithParameter<Credentials, Account>
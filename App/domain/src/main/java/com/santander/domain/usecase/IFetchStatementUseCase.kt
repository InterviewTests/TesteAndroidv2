package com.santander.domain.usecase

import com.santander.domain.entity.business.Statement
import com.santander.domain.usecase.core.UseCase

interface IFetchStatementUseCase: UseCase.FromObservable.WithParameter<Int, List<Statement>>
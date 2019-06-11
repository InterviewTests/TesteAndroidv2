package com.zuptest.santander.domain.business.usecase

import com.zuptest.santander.domain.business.model.Statement

interface ListStatementsUseCase : UseCase.WithParameter<Int, List<Statement>>
package com.zuptest.domain.business.usecase

import com.zuptest.domain.business.model.Statement

interface GetStatementsUseCase : UseCase.WithParameter<String, List<Statement>>
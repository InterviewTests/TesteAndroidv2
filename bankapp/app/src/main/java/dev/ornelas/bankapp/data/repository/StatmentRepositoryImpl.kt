package dev.ornelas.bankapp.data.repository

import dev.ornelas.banckapp.domain.model.Statement
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.StatementRepository
import dev.ornelas.bankapp.data.datasource.api.retrofit.BankApiService
import dev.ornelas.bankapp.data.datasource.api.retrofit.exceptions.statementApiErrorFromCodeException

class StatmentRepositoryImpl(private val bankApiService: BankApiService) : StatementRepository {


    override suspend fun GetStatements(idUser: Int): Result<List<Statement>>? {

        try {
            val statementResponse = bankApiService.getStatements(idUser.toString())
            if (statementResponse.error.code != null) {
                return Result.error(statementApiErrorFromCodeException(statementResponse.error))
            }

            val statements = statementResponse.statementList.map {
                it.toStatementModel()
            }

            return Result.success(statements)
        } catch (ex: Exception) {
            return Result.error(ex)
        }

    }
}
package dev.ornelas.banckapp.domain.repository

import dev.ornelas.banckapp.domain.model.Statement
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result

interface StatementRepository {

    suspend fun  GetStatements(idUser: Int) : Result<List<Statement>>?
}
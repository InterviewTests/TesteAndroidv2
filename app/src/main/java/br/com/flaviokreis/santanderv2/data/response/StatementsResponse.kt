package br.com.flaviokreis.santanderv2.data.response

import br.com.flaviokreis.santanderv2.data.model.Error
import br.com.flaviokreis.santanderv2.data.model.Statement

data class StatementsResponse(
    val statementList: List<Statement>,
    val error: Error?
)
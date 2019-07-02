package io.github.maikotrindade.bankapp.statement.model

import io.github.maikotrindade.bankapp.base.network.Error

class StatementsResponse(var statementList: List<Statement>, var error: Error?)

data class Statement(

    var title: String,
    var desc: String,
    var date: String,
    var value: Double

)
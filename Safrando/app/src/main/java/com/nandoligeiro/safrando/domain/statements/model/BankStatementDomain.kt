package com.nandoligeiro.safrando.domain.statements.model

data class BankStatementDomain(
    val nameStatement: String,
    val description: String,
    val amount: String,
    val statementDate: String
)

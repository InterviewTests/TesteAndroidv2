package com.nandoligeiro.safrando.datasource.bankstatement.entity

data class BankStatementEntity(
    val nameStatement: String,
    val description: String,
    val amount: String,
    val statementDate: String
)

package com.accenture.santander.entity

data class ListStatement(
    var statementList: MutableList<Statement?> = ArrayList(),
    var error: Error
)
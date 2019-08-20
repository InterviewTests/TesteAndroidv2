package com.example.mybank.data.remote.model

data class RecordTransactionResponse(
    val statementList: List<RecordTransaction>?,
    val error: RecordError?
)
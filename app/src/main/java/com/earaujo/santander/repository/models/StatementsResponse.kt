package com.earaujo.santander.repository.models

data class StatementsResponse(
    var statementList: List<StatementsListModel>?,
    var error: ErrorModel?
)
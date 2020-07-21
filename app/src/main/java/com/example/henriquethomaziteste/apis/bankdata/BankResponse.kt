package com.example.henriquethomaziteste.apis.bankdata

class BankResponse (
    var statementList: MutableList<BankStatement> = ArrayList(),
    var error: Any?
) {
}
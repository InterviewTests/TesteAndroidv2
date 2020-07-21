package com.example.henriquethomaziteste.events

import com.example.henriquethomaziteste.apis.bankdata.BankStatement

class BankStatementsEvent(
    var error: Boolean? = false,
    var statements: MutableList<BankStatement>?
) {
}
package com.jisellemartins.bank.model

class DetailsUser(
    var id: String,
    var loginId: String,
    var account: String,
    var balance: Int,
    var user: User,
    var statementsList: List<Statements>
)
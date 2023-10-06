package com.jisellemartins.bank.model

class DetailsUser(
    id: String,
    loginId: String,
    account: String,
    balance: Int,
    user: User,
    statements: List<Statements>
)
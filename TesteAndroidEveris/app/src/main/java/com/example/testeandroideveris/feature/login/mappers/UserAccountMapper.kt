package com.example.testeandroideveris.feature.login.mappers

import com.example.testeandroideveris.feature.login.data.UserAccount
import com.example.testeandroideveris.feature.login.data.UserAccountData

object UserAccountMapper {
    fun mapFrom(from: UserAccountData): UserAccount = UserAccount(from.userId, from.name, from.bankAccount, from.agency, from.balance)
}
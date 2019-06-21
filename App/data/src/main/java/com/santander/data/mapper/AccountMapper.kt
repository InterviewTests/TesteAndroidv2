package com.santander.data.mapper

import com.santander.data.mapper.base.BaseMapper
import com.santander.data.source.remote.entity.response.UserAccountResponse
import com.santander.domain.entity.business.BankAccount
import com.santander.domain.entity.business.Money
import com.santander.domain.entity.business.UserAccount
import com.santander.domain.exception.InvalidAccountException

class AccountMapper : BaseMapper<UserAccountResponse, UserAccount> {

    override fun toEntity(from: UserAccountResponse): UserAccount {
        return UserAccount(
            bankAccount = BankAccount(
                account = from.bankAccount.orEmpty(),
                agency = from.agency.orEmpty()
                    .takeIf { from.agency?.length == 9 } ?: throw InvalidAccountException()
            ),
            balance = Money(value = from.balance ?: 0.0),
            name = from.name.orEmpty(),
            userId = from.userId ?: throw Exception()
        )
    }

}
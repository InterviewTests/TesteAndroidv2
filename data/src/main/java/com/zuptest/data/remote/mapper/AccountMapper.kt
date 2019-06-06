package com.zuptest.data.remote.mapper

import com.zuptest.data.remote.response.AccountResponse
import com.zuptest.domain.business.model.Account
import com.zuptest.domain.business.model.BankInfo
import com.zuptest.domain.business.model.Money

fun AccountResponse.mapFrom() = Account(
    id = this.userId,
    holder = this.userName,
    balance = Money(this.balance),
    bankInfo = BankInfo(
        account = this.bankAccount,
        agency = this.bankAgency
    )
)

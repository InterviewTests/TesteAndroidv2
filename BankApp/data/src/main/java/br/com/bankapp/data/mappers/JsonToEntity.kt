package com.mvvmclean.trendingrepos.data.mappers

import br.com.bankapp.data.api.network_responses.UserAccountResponse
import br.com.bankapp.data.db.entity.UserAccountEntity


internal fun UserAccountResponse.toEntity(): UserAccountEntity {
    return UserAccountEntity(
        this.userId!!,
        this.name,
        this.bankAccount,
        this.agency,
        this.balance
    )
}
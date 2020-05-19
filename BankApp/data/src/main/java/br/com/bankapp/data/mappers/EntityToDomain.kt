package com.mvvmclean.trendingrepos.data.mappers

import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.bankapp.domain.models.UserAccount


internal fun UserAccountEntity.toDomain(): UserAccount {
    return UserAccount(
        this.userId,
        this.name,
        this.bankAccount,
        this.agency,
        this.balance
    )
}
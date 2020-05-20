package br.com.bankapp.data.mappers

import androidx.paging.DataSource
import br.com.bankapp.data.db.entity.StatementEntity
import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.domain.models.UserAccount


internal fun UserAccountEntity.toDomain(): UserAccount {
    return UserAccount(
        userId = this.userId,
        name = this.name,
        bankAccount = this.bankAccount,
        agency = this.agency,
        balance = this.balance
    )
}

internal fun StatementEntity.toDomain(): Statement {
    return Statement(
        id = this.id,
        title = this.title,
        date = this.date,
        desc = this.description,
        value = this.value,
        userId = this.userId
    )
}

internal fun DataSource.Factory<Int, StatementEntity>.toDomain(): DataSource.Factory<Int, Statement> {
    return map {
        it.toDomain()
    }
}
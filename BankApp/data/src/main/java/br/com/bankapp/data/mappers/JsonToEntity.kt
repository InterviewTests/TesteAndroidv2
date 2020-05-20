package br.com.bankapp.data.mappers

import br.com.bankapp.data.api.network_responses.StatementResponse
import br.com.bankapp.data.api.network_responses.UserAccountResponse
import br.com.bankapp.data.db.entity.StatementEntity
import br.com.bankapp.data.db.entity.UserAccountEntity
import java.text.SimpleDateFormat
import java.util.*


internal fun UserAccountResponse.toEntity(): UserAccountEntity {
    return UserAccountEntity(
        userId = this.userId!!,
        name = this.name,
        bankAccount = this.bankAccount,
        agency = this.agency,
        balance = this.balance
    )
}

internal fun StatementResponse.toEntity(userId: Int): StatementEntity {
    return StatementEntity(
        title = this.title!!,
        date = convertStringToDate(this.date!!),
        description = this.description!!,
        value = this.value!!,
        userId = userId
    )
}

internal fun convertStringToDate(date: String): Date {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date) ?: Date()
}
package br.com.rms.bankapp.data.model

import br.com.rms.bankapp.data.local.database.entity.Account

data class UserBody(
    val userAccount: Account?,
    val error: Error?
)
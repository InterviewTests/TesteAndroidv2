package br.com.rms.bankapp.data.remote.model

import br.com.rms.bankapp.data.local.database.entity.Account

data class UserResponse(
    val userAccount: Account?,
    val error: Error?
)
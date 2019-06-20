package com.farage.testeandroidv2.domain.model

import java.io.Serializable


data class UserAccount(
    val userId: String?,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: Double?
) : Serializable
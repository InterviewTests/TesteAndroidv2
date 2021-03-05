package br.com.silas.domain.user

import java.io.Serializable

data class User (
    val id: Int,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: Double
) : Serializable
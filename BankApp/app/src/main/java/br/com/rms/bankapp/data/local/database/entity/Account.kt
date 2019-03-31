package br.com.rms.bankapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey val userId: Int?,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: Double?
)
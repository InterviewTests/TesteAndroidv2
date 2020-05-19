package br.com.bankapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class UserAccountEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val name: String?,
    @ColumnInfo(name = "bank_account")
    val bankAccount: String?,
    val agency: String?,
    val balance: Double?
)
package com.tata.bank.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_credentials")
data class LoginCredentialEntity(
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    @ColumnInfo(name = "credentials") val credentials: String
)
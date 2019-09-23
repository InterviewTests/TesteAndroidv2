package com.gustavo.bankandroid.features.login.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserInfoDto(
    val id: Int,
    val username: String,
    val password: String,
    val name: String,
    val account: String,
    val balance: Long,
    @PrimaryKey
    val primaryKey: Int = 1
)
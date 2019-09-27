package com.gustavo.bankandroid.datasource.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserInfoDto(
    val userId : Int,
    val name : String,
    val bankAccount : Int,
    val agency : Int,
    val balance : Double,
    @PrimaryKey
    val primaryKey: Int = 1
)
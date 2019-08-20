package com.example.mybank.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    var userId: Int?,

    @ColumnInfo(name="name")
    var name: String?,

    @ColumnInfo(name="banckAccount")
    var bankAccount: String?,

    @ColumnInfo(name="agency")
    var agency: String?,

    @ColumnInfo(name="balance")
    var balance: Double?
)
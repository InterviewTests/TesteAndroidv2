package com.accenture.santander.dataManager.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int = 0,

    @ColumnInfo(name = "IDUSER")
    var iduser: Int = 0,

    @ColumnInfo(name = "NAME")
    var name: String = "",

    @ColumnInfo(name = "BANKACCOUNT")
    var bankAccount: String = "",


    @ColumnInfo(name = "AGENCY")
    var agency: String = "",


    @ColumnInfo(name = "BALANCE")
    var balance: Double = 0.0
)
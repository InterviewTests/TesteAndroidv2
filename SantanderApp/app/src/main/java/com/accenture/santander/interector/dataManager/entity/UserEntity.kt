package com.accenture.santander.interector.dataManager.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.accenture.santander.entity.UserAccount

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
) {

    fun mapper(user: UserAccount): UserEntity {
        this.iduser = user.userId
        this.name = user.name
        this.bankAccount = user.bankAccount
        this.agency = user.agency
        this.balance = user.balance
        return this
    }
}
package br.com.rms.bankapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

const val USER_ID = 0

@Entity()
data class User(
    @PrimaryKey val id: Int?,
    val user: String?,
    val password: String?,
    var accountId: Int?
)
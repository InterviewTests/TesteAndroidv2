package br.com.rms.bankapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("id"), Index("accountId")],
    foreignKeys = [ForeignKey(
        entity = Account::class,
        parentColumns = ["userId"],
        childColumns = ["accountId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class User(
    @PrimaryKey val id: Int,
    val user: String,
    val password: String,
    val accountId: Int
)
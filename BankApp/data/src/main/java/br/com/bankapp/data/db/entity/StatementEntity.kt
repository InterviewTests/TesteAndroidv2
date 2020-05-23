package br.com.bankapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "statements",
    foreignKeys = [
        ForeignKey(onDelete = ForeignKey.CASCADE,
            entity = UserAccountEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"])])
data class StatementEntity(
    @PrimaryKey
    val id : Long,
    val title: String,
    val description: String,
    val date: Date,
    val value: Double,
    @ColumnInfo(name = "user_id")
    val userId: Int
)
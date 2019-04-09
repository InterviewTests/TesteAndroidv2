package br.com.rms.bankapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id")])
data class Statement(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val title: String?,
    val desc: String?,
    val date: String?,
    val value: Double?
)
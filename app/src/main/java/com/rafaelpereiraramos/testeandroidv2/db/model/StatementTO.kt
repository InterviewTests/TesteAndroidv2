package com.rafaelpereiraramos.testeandroidv2.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Entity(foreignKeys = [
    ForeignKey(entity = UserTO::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE)
])
data class StatementTO(
    @SerializedName("date") val date: Date = Date(),
    @SerializedName("desc") val desc: String = "",
    @SerializedName("title") val title: String = "",
    val user_id: Int = -1,
    @SerializedName("value") val value: Float = 0.0f
) {
    constructor(statement: StatementTO, userId: Int) : this(statement.date, statement.desc, statement.title, userId, statement.value)

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
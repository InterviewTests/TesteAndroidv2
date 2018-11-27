package com.rafaelpereiraramos.testeandroidv2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Entity
data class StatementTO(
    @SerializedName("date") val date: Date,
    @SerializedName("desc") val desc: String,
    @SerializedName("title") val title: String,
    @SerializedName("value") val value: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
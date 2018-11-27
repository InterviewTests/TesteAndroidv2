package com.rafaelpereiraramos.testeandroidv2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
@Entity
data class ParameterTO(
    @PrimaryKey val key: String,
    val value: String
)
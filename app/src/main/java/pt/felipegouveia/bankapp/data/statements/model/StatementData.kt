package pt.felipegouveia.bankapp.data.statements.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatementData(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("date")
    val date: Date? = null,
    @SerializedName("value")
    val value: Double = 0.0
)
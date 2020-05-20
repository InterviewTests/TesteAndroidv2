package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class StatementDataResponseDTO(
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("desc")
    val desc: String? = "",
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("value")
    val value: Double? = 0.0
)
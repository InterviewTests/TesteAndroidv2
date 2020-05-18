package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class StatementDataResponseDTO(
    @SerializedName("title")
    private val title: String? = "",
    @SerializedName("desc")
    private val desc: String? = "",
    @SerializedName("date")
    private val date: String? = "",
    @SerializedName("value")
    private val value: Double? = 0.0
)
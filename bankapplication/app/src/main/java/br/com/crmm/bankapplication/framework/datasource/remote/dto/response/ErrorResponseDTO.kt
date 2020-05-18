package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ErrorResponseDTO(
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)
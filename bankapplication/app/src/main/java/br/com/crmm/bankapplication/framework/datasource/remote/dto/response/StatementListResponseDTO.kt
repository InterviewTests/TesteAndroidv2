package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class StatementListResponseDTO(
    @SerializedName("statementList")
    val statementDataResponseDTOList: List<StatementDataResponseDTO>? = null,
    @SerializedName("error")
    val errorResponseDTO: ErrorResponseDTO? = null
)
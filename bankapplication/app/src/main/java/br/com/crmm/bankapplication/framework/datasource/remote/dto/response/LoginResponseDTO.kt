package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    @SerializedName("userAccount") val userAccountResponseDTO: UserAccountResponseDTO? = null,
    @SerializedName("error") val errorResponseDTO: ErrorResponseDTO? = null
)
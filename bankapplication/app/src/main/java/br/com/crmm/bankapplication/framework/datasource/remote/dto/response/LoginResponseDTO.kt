package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    @SerializedName("userAccount") var userAccountDTO: UserAccountDTO? = null,
    @SerializedName("error") var errorDTO: ErrorDTO? = null
)
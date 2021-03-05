package br.com.silas.data.remote.login

import br.com.silas.data.remote.ErrorResponse
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("userAccount") val userAccountResponse: UserAccountResponse,
    @SerializedName("error") val errorResponse: ErrorResponse
)

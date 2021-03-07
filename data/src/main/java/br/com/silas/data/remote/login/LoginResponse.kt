package br.com.silas.data.remote.login

import br.com.silas.data.remote.ErrorResponse
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("userAccount") val userEntity: UserEntity,
    @SerializedName("error") val errorResponse: ErrorResponse
)

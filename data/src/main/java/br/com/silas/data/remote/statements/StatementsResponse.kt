package br.com.silas.data.remote.statements

import br.com.silas.data.remote.ErrorResponse
import com.google.gson.annotations.SerializedName

class StatementsResponse(
    @SerializedName("error") val errorResponse: ErrorResponse,
    @SerializedName("statementList" ) val statements: List<StatementsEntity>
)

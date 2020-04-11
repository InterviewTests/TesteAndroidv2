package pt.felipegouveia.bankapp.data.statements.model

import com.google.gson.annotations.SerializedName
import pt.felipegouveia.bankapp.data.common.Error

data class StatementsData(
    @SerializedName("statementList")
    val statementDataList: List<StatementData>?,
    @SerializedName("error")
    val error: Error?
)
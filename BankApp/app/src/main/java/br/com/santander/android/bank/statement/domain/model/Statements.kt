package br.com.santander.android.bank.statement.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Statements(
    @SerializedName("statementList")
    val list: List<Statement>
): Serializable
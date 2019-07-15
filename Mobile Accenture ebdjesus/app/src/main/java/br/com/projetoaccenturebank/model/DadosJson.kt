package br.com.projetoaccenturebank.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DadosJson : Serializable {
    @SerializedName("userAccount")
    var login: Login? = null
    @SerializedName("error")
    var login_error : ErrorLogin? = null

    @SerializedName("statementList")
    var statementList : ArrayList<StatementList>? = null
}
package br.com.teste.santander.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Json : Serializable {
    @SerializedName("userAccount")
    var login: Login? = null
    @SerializedName("error")
    var login_error : Erros? = null

    @SerializedName("statementList")
    var statementList : ArrayList<Dados>? = null
}
package br.com.projetoaccenturebank.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ErrorLogin : Serializable {

    @SerializedName("error")
    var code : Int? = null
    @SerializedName("message")
    var message : String? = null
}
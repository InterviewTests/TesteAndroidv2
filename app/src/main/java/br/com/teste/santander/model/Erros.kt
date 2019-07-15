package br.com.teste.santander.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Erros : Serializable{
    @SerializedName("error")
    var code : Int? = null
    @SerializedName("message")
    var message : String? = null
}
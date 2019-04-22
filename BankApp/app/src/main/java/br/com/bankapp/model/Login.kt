package br.com.bankapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Login: Serializable {

    @SerializedName("username")
    @Expose
    var uesrename: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}
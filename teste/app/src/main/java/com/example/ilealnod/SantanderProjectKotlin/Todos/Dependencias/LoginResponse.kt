package com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias

import com.google.gson.annotations.SerializedName

// Classe que pega os Dados do cliente / resposta da APi

class LoginResponse {

    @SerializedName("userAccount")
    var clientData: ClientData? = null
}

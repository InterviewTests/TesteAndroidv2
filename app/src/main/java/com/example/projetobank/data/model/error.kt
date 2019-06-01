package com.example.projetobank.data.model

import com.google.gson.annotations.SerializedName

class error
    (  @SerializedName("code")
       val code: Int,
       @SerializedName("message")
       val message: String){
}
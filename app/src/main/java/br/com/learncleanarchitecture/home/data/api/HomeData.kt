package br.com.learncleanarchitecture.home.data.api

import com.google.gson.annotations.SerializedName

class HomeData {

    data class HomeResult(val statementList: List<Statment>)

    data class Statment(
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("desc")
        val desc: String? = "",
        @SerializedName("date")
        val date: String? = "",
        @SerializedName("value")
        val value: Float? = 0F
        )
}
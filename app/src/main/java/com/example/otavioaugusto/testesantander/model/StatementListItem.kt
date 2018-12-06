package com.example.otavioaugusto.testesantander.model

import com.google.gson.annotations.SerializedName

data class StatementListItem(@SerializedName("title")
                             val title: String = "",
                             @SerializedName("desc")
                             val desc: String = "",
                             @SerializedName("date")
                             val date: String = "",
                             @SerializedName("value")
                             val value: Double = 0.0)
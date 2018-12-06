package com.example.otavioaugusto.testesantander.model

import com.google.gson.annotations.SerializedName

data class Statements(@SerializedName("statementList")
                      val statementList: List<StatementListItem>?,
                      @SerializedName("error")
                      val error: Error)
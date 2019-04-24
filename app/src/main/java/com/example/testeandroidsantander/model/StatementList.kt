package com.example.testeandroidsantander.model

import com.google.gson.annotations.SerializedName

data class StatementList (@SerializedName("statementList") val statementList: List<Statement>)
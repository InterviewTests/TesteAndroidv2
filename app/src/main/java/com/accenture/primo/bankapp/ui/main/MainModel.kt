package com.accenture.primo.bankapp.ui.main

import com.accenture.primo.bankapp.model.Error
import com.accenture.primo.bankapp.model.Statement
import com.accenture.primo.bankapp.model.User
import com.google.gson.annotations.SerializedName

class MainModel {

    class MainViewModel(
        val user: User,
        val statements: List<Statement>
    )

    class MainResponse(
        @SerializedName(value = "statementList")
        val statements: List<Statement>,
        @SerializedName(value = "error")
        val error: Error
    )
}

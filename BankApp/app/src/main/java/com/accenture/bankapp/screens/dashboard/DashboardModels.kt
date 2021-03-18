package com.accenture.bankapp.screens.dashboard

import com.accenture.bankapp.network.models.Statement
import java.util.*

data class DashboardViewModel(
    var listStatements: ArrayList<Statement>? = null
)

data class DashboardRequest(
    var userId: Int? = null
)

data class DashboardResponse(
    var listStatements: ArrayList<Statement>? = null
)
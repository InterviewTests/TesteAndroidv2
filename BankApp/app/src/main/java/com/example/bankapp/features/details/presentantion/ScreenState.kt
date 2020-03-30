package com.example.bankapp.features.details.presentantion

import com.example.bankapp.features.details.data.service.StatementResponse

sealed class ScreenState {
    data class Statements(val statements: List<StatementResponse>) : ScreenState()
    object Logoff : ScreenState()
    object Error : ScreenState()
}
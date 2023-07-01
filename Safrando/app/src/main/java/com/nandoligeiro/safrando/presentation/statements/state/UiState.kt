package com.nandoligeiro.safrando.presentation.statements.state

sealed class UiBankStatementState<out T> {
    object Loading : UiBankStatementState<Nothing>()
    data class Success<T>(val data: T) : UiBankStatementState<T>()
    data class Error(val msg: String) : UiBankStatementState<Nothing>()
}

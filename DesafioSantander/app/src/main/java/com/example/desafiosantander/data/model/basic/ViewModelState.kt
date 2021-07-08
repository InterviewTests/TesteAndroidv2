package com.example.desafiosantander.data.model.basic

import com.example.desafiosantander.data.model.response.Error

data class ViewModelState<T>(
    val status: Status,
    val model: T? = null,
    val errors: Error? = null
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}
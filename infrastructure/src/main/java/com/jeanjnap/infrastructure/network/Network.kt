package com.jeanjnap.infrastructure.network

interface Network {
    fun hasActiveInternetConnection(): Boolean
}

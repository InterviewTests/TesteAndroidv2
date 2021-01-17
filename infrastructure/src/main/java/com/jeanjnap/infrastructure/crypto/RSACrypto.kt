package com.jeanjnap.infrastructure.crypto

interface RSACrypto {
    fun encrypt(value: String): String
    fun decrypt(value: String): String
}

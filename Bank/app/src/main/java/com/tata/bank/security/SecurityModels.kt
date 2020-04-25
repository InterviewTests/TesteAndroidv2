package com.tata.bank.security

data class CipherData(
    val encrypted: ByteArray,
    val iv: ByteArray
)
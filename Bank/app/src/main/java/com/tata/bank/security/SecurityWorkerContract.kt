package com.tata.bank.security

interface SecurityWorkerContract {
    fun encrypt(decryptedBytes: ByteArray): CipherData
    fun decrypt(encryptedData: CipherData): ByteArray?
}
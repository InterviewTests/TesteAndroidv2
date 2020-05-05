package com.tata.bank.security

interface ISecurityWorker {
    fun encrypt(decryptedBytes: ByteArray): CipherData
    fun decrypt(encryptedData: CipherData): ByteArray?
}
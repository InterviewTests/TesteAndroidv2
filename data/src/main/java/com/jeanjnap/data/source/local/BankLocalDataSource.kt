package com.jeanjnap.data.source.local

interface BankLocalDataSource {
    fun saveEncryptedUser(user: String)
    fun getEncryptedUser(): String?
}
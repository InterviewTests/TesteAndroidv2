package com.jeanjnap.data.source.local

class BankLocalDataSourceImpl(
    private val cache: Cache
): BankLocalDataSource {
    override fun saveEncryptedUser(user: String) {
        return cache.set(USER, user)
    }

    override fun getEncryptedUser(): String? {
        return cache.nullableGet(USER, String::class.java)
    }

    companion object {
        private const val USER = "USER"
    }
}

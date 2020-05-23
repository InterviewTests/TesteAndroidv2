package br.com.bankapp.domain.repository

import androidx.lifecycle.LiveData
import br.com.bankapp.domain.models.UserAccount

interface UserAccountRepository {

    suspend fun attemptLogin(user: String, password: String)

    fun getUserAccount(userId: Int): LiveData<UserAccount>
}
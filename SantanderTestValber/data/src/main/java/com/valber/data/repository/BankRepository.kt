package com.valber.data.repository

import com.valber.data.model.*
import com.valber.data.remote.ServiceBank
import com.valber.data.model.User
import io.reactivex.Single

interface BankRepository {

    fun login(user: User): Single<UserAccount>
    fun statement(id: Int): Single<List<Statement>>

}
class BankRepositoryImpl constructor(
    private val serviceBank: ServiceBank
) : BankRepository {

    override fun login(user: User): Single<UserAccount> {
        return serviceBank.logar(user)
    }

    override fun statement(id: Int): Single<List<Statement>> {
        return serviceBank.statement(id)
    }


}

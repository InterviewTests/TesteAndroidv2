package com.valber.data.remote

import com.valber.data.model.*
import com.valber.data.model.User
import io.reactivex.Single

/**
 * create by valber siqueira
 * adicionando os metodos presentes na class Retrofit
 * interface de conexao externa
 */
interface ServiceBank {
    fun logar(user: User): Single<UserAccount>
    fun statement(id: Int): Single<List<Statement>>

}
class ServiceBankImpl(private val api: BankApi) : ServiceBank {

    override fun logar(user: User) = api
        .logar(user)
        .map { it.userAccount.mapUserAccout() }

    override fun statement(id: Int) =
        api.getStatement(id)
            .map { it.statementList.mapStatement() }
}

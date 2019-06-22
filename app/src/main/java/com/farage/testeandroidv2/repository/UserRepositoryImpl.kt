package com.farage.testeandroidv2.repository

import com.farage.testeandroidv2.datasource.UserDataSource
import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.toMoney

class UserRepositoryImpl(private var userDataSource: UserDataSource) : UserRepository {

    override suspend fun userLogin(user: String, password: String): ResultState<UserAccount> {
        userDataSource.doUserLogin().let { it ->
            it?.data.let {
                val userModel = UserAccount(
                    userId = it?.userAccountResponse?.userId,
                    name = it?.userAccountResponse?.name,
                    bankAccount = it?.userAccountResponse?.bankAccount,
                    agency = it?.userAccountResponse?.agency,
                    balance = it?.userAccountResponse?.balance.toString().toMoney()
                )

                return ResultState.success(userModel)
            }
        }
    }
}
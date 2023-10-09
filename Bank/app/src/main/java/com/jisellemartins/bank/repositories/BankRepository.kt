package com.jisellemartins.bank.repositories

import com.jisellemartins.bank.model.DetailsUser
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.network.Output
import com.jisellemartins.bank.service.BankService
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.Objects

class BankRepository constructor(
    private val service: BankService
) {



/*    suspend fun postLogin(login: Login): Output<User?> {
        val response = service.postLogin(login)

        if (response.isSuccessful) {
            return Output.Success(response.body())
        }
        return Output.NetworkError
    }*/

    suspend fun postLogin(login: Login) = flow {
        emit(Output.Loading())
        val response = service.postLogin(login)

        if (response.isSuccessful) {
            emit(Output.Success(response.body()))
        }else{
            emit(Output.NetworkError)
        }

    }

    suspend fun getDetailsUser(idUser:String): DetailsUser? {
        val response = service.getDetailsUser(idUser)

        if (response.isSuccessful) {
            return response.body()?.get(0)
        }
        return null
    }


}
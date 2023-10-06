package com.jisellemartins.bank.repositories

import com.jisellemartins.bank.model.DetailsUser
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import com.jisellemartins.bank.service.BankService
import java.util.Objects

class BankRepository constructor(
    private val service: BankService
) {



    suspend fun postLogin(login: Login): User? {
        val response = service.postLogin(login)

        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getDetailsUser(idUser:String): DetailsUser? {
        val response = service.getDetailsUser(idUser)

        if (response.isSuccessful) {
            return response.body()?.get(0)
        }
        return null
    }


}
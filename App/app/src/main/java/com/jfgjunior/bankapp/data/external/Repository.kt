package com.jfgjunior.bankapp.data.external

import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.data.models.User
import com.jfgjunior.bankapp.data.models.UserCredentials
import io.reactivex.Single

interface Repository {

    fun loginUser(userCredentials: UserCredentials): Single<ResponseWrapper<User>>

    fun getTransactions(userId: Int): Single<ResponseWrapper<List<Statement>>>

    fun saveUser(userCredentials: UserCredentials)

    fun getUser(): UserCredentials?

    fun clearUser()
}
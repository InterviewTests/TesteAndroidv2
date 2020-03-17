package dev.ornelas.banckapp.domain.repository

import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result

interface UserRepository {

    suspend fun GetUser(user:  String, password: String) : Result<User>

    fun AddUser(user: User)

    fun GetSavedUser(): Result<User>
}
package dev.ornelas.bankapp.data.repository

import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.UserRepository
import dev.ornelas.bankapp.data.datasource.api.retrofit.BankApiService
import dev.ornelas.bankapp.data.datasource.api.retrofit.exceptions.userApiErrorFromCodeException
import dev.ornelas.bankapp.data.datasource.local.SharedPreferenceDataSource

class UserRepositoryImpl(
    private val bankApiService: BankApiService,
    private val sharedPreferenceDataSource: SharedPreferenceDataSource
) : UserRepository {

    override suspend fun GetUser(user: String, password: String): Result<User> {
        try {
            val loginResponse = bankApiService.login(user, password)
            if (loginResponse.error.code != null) {
                return Result.error(userApiErrorFromCodeException(loginResponse.error))
            }
            return Result.success(loginResponse.toUserModel())
        } catch (ex: Exception) {
            return Result.error(ex)
        }
    }

    override fun AddUser(user: User) {
        sharedPreferenceDataSource.put(user, "user")
    }

    override fun GetSavedUser(): Result<User> {
        return Result.success(sharedPreferenceDataSource.get<User>("user"))
    }

    override fun RemoveSavedUser() {
        sharedPreferenceDataSource.put(null, "user")
    }
}
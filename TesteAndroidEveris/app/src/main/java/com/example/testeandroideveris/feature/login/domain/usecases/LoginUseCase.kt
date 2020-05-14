package com.example.testeandroideveris.feature.login.domain.usecases

import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.mappers.UserAccountMapper
import kotlinx.coroutines.flow.map
import java.util.regex.Pattern

class LoginUseCase(private val repository: LoginRepository){

    suspend fun login(request: LoginRequestData) = repository.login(request)
        .map { value: LoginResponseData -> UserAccountMapper.mapFrom(value.userAccount) }

    suspend fun getLastUser() = repository.getLastUser()

    fun validate(loginRequest: LoginRequestData) = when {
        loginRequest.user.isBlank() -> LoginDataState.INVALID_USER
        !android.util.Patterns.EMAIL_ADDRESS.matcher(loginRequest.user).matches() -> LoginDataState.INVALID_USER
        loginRequest.password.isBlank() -> LoginDataState.INVALID_PASSWORD
        !isValidPassword(loginRequest.password) -> LoginDataState.INVALID_PASSWORD
        else -> LoginDataState.VALID_SUCCESS
    }

    private fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[@_.]).*\$"
        )
        return PASSWORD_PATTERN.matcher(password).matches()
    }
}
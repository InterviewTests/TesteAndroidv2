package com.example.testeandroideveris.feature.login.domain.usecases

import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.mappers.UserAccountMapper
import com.example.testeandroideveris.feature.util.BankUtil.digitCalc
import com.example.testeandroideveris.feature.util.BankUtil.weightCPF
import kotlinx.coroutines.flow.map
import java.util.regex.Pattern

class LoginUseCase(private val repository: LoginRepository){

    suspend fun login(request: LoginRequestData) = repository.login(request)
        .map { value: LoginResponseData -> UserAccountMapper.mapFrom(value.userAccount) }

    suspend fun getLastUser() = repository.getLastUser()

    fun validate(loginRequest: LoginRequestData) = when {
        loginRequest.user.isBlank() -> LoginDataState.INVALID_USER
        validEmailOrDocument(loginRequest) -> LoginDataState.INVALID_USER
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

    private fun validEmailOrDocument(loginRequest: LoginRequestData): Boolean {
        return if (loginRequest.user.contains("@", false)) {
            !android.util.Patterns.EMAIL_ADDRESS.matcher(loginRequest.user).matches()
        } else {
            !isValidCPF(loginRequest.user)
        }
    }

    private fun isValidCPF(document: String?): Boolean {
        if (document == null || document.length != 11) return false
        val digit1: Int = digitCalc(document.substring(0, 9), weightCPF)
        val digit2: Int = digitCalc(document.substring(0, 9) + digit1, weightCPF)
        return document == document.substring(0, 9) + digit1 + digit2
    }
}
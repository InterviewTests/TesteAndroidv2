package com.br.myapplication.domain.usecase

import com.br.myapplication.data.model.UserAccount
import com.br.myapplication.data.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    fun getUser() = userRepository.getUser()
    fun saveUser(userAccount: UserAccount) = userRepository.saveUserPreferences(userAccount)
    fun deleteUser() = userRepository.deletePreferences()
}
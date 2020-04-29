package com.br.myapplication.usecase

import com.br.myapplication.model.UserAccount
import com.br.myapplication.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    fun getUser() = userRepository.getUser()
    fun saveUser(userAccount: UserAccount) = userRepository.saveUserPreferences(userAccount)
    fun deleteUser() = userRepository.deletePreferences()
}
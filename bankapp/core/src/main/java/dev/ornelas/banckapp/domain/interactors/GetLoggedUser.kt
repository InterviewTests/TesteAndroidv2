package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.repository.UserRepository

class GetLoggedUser(private val userRepository: UserRepository) {

    operator  fun invoke()  = userRepository.GetSavedUser()
}
package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.repository.UserRepository

class RemoveLoggedUser(private val userRepository: UserRepository) {
    operator fun invoke() = userRepository.RemoveSavedUser()
}


package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.repository.UserRepository

class SaveLoggedUser(private val userRepository: UserRepository) {

    operator  fun  invoke(user: User) = userRepository.AddUser(user)


}
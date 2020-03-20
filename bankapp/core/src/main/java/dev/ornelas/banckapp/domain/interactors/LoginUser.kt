package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.repository.UserRepository

class LoginUser(private  val  userRepository: UserRepository) {
      suspend operator  fun invoke(user: String, password: String)  = userRepository.GetUser(user, password)
}
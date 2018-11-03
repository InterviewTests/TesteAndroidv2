package com.ygorcesar.testeandroidv2.login.domain

import com.ygorcesar.testeandroidv2.base.common.validations.Validations
import com.ygorcesar.testeandroidv2.login.data.LoginRepository
import io.reactivex.Completable
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val loginRepository: LoginRepository) {

    fun login(user: String, password: String): Completable {
        return when {
            user.isBlank() -> Completable.error(LoginBusiness.UserInvalid)
            !Validations.isCpfValid(user) && !Validations.isEmailValid(user) -> Completable.error(
                LoginBusiness.UserInvalid
            )
            password.isBlank() -> Completable.error(LoginBusiness.PasswordInvalid)
            !Validations.hasSpecialCharacter(password) -> Completable.error(LoginBusiness.PasswordNeedSpecialCharacter)
            !Validations.hasCapitalizedLetter(password) -> Completable.error(LoginBusiness.PasswordNeedCapitalizedLetter)
            else -> loginRepository.login(user, password)
        }
    }
}
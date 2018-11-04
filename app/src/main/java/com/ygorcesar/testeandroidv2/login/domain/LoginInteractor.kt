package com.ygorcesar.testeandroidv2.login.domain

import com.ygorcesar.testeandroidv2.base.common.validations.Validations
import com.ygorcesar.testeandroidv2.login.data.LoginRepository
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val loginRepository: LoginRepository) {

    fun login(user: String, password: String): Single<UserAccount> {
        return when {
            user.isBlank() -> Single.error(LoginBusiness.UserInvalid)
            !Validations.isCpfValid(user) && !Validations.isEmailValid(user) -> Single.error(
                LoginBusiness.UserInvalid
            )
            password.isBlank() -> Single.error(LoginBusiness.PasswordInvalid)
            !Validations.hasSpecialCharacter(password) -> Single.error(LoginBusiness.PasswordNeedSpecialCharacter)
            !Validations.hasCapitalizedLetter(password) -> Single.error(LoginBusiness.PasswordNeedCapitalizedLetter)
            else -> loginRepository.login(user, password)
        }
    }
}
package com.ygorcesar.testeandroidv2.login.domain

import com.ygorcesar.testeandroidv2.base.common.exception.BusinessException

sealed class LoginBusiness {

    object UserInvalid : BusinessException()
    object PasswordInvalid : BusinessException()
    object PasswordNeedSpecialCharacter : BusinessException()
    object PasswordNeedCapitalizedLetter : BusinessException()
    object PasswordNeedNumber : BusinessException()

}
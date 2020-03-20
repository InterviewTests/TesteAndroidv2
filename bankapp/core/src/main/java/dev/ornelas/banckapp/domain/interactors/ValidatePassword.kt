package dev.ornelas.banckapp.domain.interactors

import dev.ornelas.banckapp.domain.exceptions.InvalidPasswordException
import dev.ornelas.banckapp.domain.model.datatype.Result

class ValidatePassword {

    operator fun  invoke(password: String): Result<Unit> {
       if (!"(?=.*[a-z0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{3,}".toRegex().matches(password) ){
           return Result.error (InvalidPasswordException("A senha deve contem letra maiuscula, um caracter especial e um caracter alfanumérico"))
       }

        return Result.success(Unit)
    }
}
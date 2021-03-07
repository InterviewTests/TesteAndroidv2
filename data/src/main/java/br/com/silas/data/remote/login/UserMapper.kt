package br.com.silas.data.remote.login

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.User

class UserMapper {
    fun mapperUserAccountResponseToUser(loginResponse: LoginResponse): Pair<User, ErrorResponse> {

        val user = User(
            loginResponse.userEntity.id,
            loginResponse.userEntity.name,
            loginResponse.userEntity.bankAccount,
            loginResponse.userEntity.agency,
            loginResponse.userEntity.balance
        )

        val loginError =
            ErrorResponse(loginResponse.errorResponse.code, loginResponse.errorResponse.message)
        return Pair(user, loginError)
    }
}
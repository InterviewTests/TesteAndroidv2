package br.com.silas.data.remote.login

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.User

class UserMapper {
    fun mapperUserAccountResponseToUser(loginResponse: LoginResponse) : Pair<User, ErrorResponse> {

        val  user = User(
            loginResponse.userAccountResponse.id,
            loginResponse.userAccountResponse.name,
            loginResponse.userAccountResponse.bankAccount,
            loginResponse.userAccountResponse.agency,
            loginResponse.userAccountResponse.balance
        )

        val loginError = ErrorResponse(loginResponse.errorResponse.code, loginResponse.errorResponse.message)
        return Pair(user, loginError)
    }
}
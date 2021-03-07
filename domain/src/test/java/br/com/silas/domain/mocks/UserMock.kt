package br.com.silas.domain.mocks

import br.com.silas.domain.user.User

class UserMock {
    companion object {
        fun getUserMock() = User(1, "Jose da Silva Teste", "2050", "012314564", 3.3445)
    }
}
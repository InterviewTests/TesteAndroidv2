package com.example.desafiosantander.mock

object UserMock {
    const val USER_SUCCESS =
        "{\"userAccount\":{\"userId\":1,\"name\":\"Jose da Silva Teste\",\"bankAccount\":\"2050\",\"agency\":\"012314564\",\"balance\":3.3445},\"error\":{}}"

    const val USER_ERROR = "{\"userAccount\":{},\"error\":{\"code\":53,\"message\":\"Usuário ou senha incorreta\"}}"

}
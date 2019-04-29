package br.com.alex.bankappchallenge.features.login

const val LOGIN_SUCCESS = "{\"userAccount\":{\"userId\":1,\"name\":\"Jose da Silva Teste\",\"bankAccount\":\"2050\",\"agency\":\"012314564\",\"balance\":3.3445},\"error\":{}}"
const val LOGIN_FAILURE = "{\"userAccount\":{},\"error\":{\"code\":53,\"message\":\"Usuário ou senha incorreta\"}}"
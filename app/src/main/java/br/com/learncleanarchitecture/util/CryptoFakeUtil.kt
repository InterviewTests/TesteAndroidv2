package br.com.learncleanarchitecture.util

object CryptoFakeUtil {

    //todo: Estou com pouco tempo e o exemplo esta se estendendo demais
    //todo: assim que tiver um tempo eu procuro uma lib e faco uma lib de crypto minha

    private val cripto = "123456"

    fun encode(password: String): String {
        return password + cripto
    }

    fun decode(password: String): String {
        return password.replace("123456", "")
    }
}
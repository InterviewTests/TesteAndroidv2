package br.com.testeandroidv2.model.enums

enum class Endpoints(private val endpoint: String) {
    LOGIN("/login"),
    STATEMENTS("/statements/{0}");

    fun endpoint(): String = endpoint
}
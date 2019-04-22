package br.com.bankapp.util

class Constantes {
    companion object {

        val CPF_MASK = "###.###.###-##"
        var LENGHT_CPF = 11

        val READ_TIMEOUT = 10000
        val CONNECT_TIMEOUT = 15000
        val WS_CONTENT_TYPE = "Content-Type"
        val WS_APPLICATION_JSON = "application/json"
        val WS_APPLICATION_X = "application/x-www-form-urlencoded"
        val WS_ACCEPT = "accept"
        val WS_CONTENT_TYPE_TEXT = "text/json"
        val REQUEST_METHOD_POST = "POST"
        val REQUEST_METHOD_GET = "GET"
        val REQUEST_PROPERTY_CONTENT_LENGTH = "Content-Length"

        val URL_LOGIN = "https://bank-app-test.herokuapp.com/api/login"
        val URL_DET_LOGIN = "https://bank-app-test.herokuapp.com/api/statements/"

        val MSG_ERRO_CONNECTION = "Por favor, verifique sua conexão."
    }
}
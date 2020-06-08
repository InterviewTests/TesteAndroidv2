package br.com.testeandroidv2.utils.http

interface HttpStatus {
    companion object {
        const val GET    = 1
        const val POST   = 2
        const val PUT    = 3
        const val DELETE = 4
    }
}
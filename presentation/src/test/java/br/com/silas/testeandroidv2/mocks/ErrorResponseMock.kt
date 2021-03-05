package br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks

import br.com.silas.domain.ErrorResponse

class ErrorResponseMock {
    companion object {
        fun getErrorResponseIsEmpty() = ErrorResponse(0,"")
        fun getErrorResponseIsNotEmpty() = ErrorResponse(0,"Teste")
    }
}
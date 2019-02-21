package com.example.androidtest.repository

abstract class ApiResponse(
    val msg: String = ""
)

class SuccessResponse : ApiResponse() {

}


class FailureResponse(msg: String) : ApiResponse(msg) {

}

class NoResponse : ApiResponse("Sem resposta. Tente novamente.") {

}

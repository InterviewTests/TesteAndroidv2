package com.example.androidtest.repository

abstract class ApiResponse {
    val message: String = ""
}

class SuccessResponse : ApiResponse() {

}


class FailureResponse : ApiResponse() {

}

class NoResponse : ApiResponse() {

}

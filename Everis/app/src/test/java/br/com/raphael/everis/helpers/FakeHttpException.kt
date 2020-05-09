package br.com.raphael.everis.helpers

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeHttpException : HttpException {

    constructor(code: Int = 500, body: ResponseBody = ResponseBody.create(MediaType.get("text/plain"), "")) : super(
        Response.error<String>(code, body))

}
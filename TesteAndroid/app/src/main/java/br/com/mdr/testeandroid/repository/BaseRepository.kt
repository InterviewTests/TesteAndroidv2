package br.com.mdr.testeandroid.repository

import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> handleResponse(response: Response<T>): T? {
        return response.body()
    }
}
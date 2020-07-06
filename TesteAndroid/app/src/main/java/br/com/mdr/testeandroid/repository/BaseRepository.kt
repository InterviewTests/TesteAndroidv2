package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.extensions.fromJsonOrNull
import br.com.mdr.testeandroid.model.api.ErrorWrapperApiModel
import br.com.mdr.testeandroid.model.business.ErrorList
import br.com.mdr.testeandroid.model.mapper.toBusinessModel
import br.com.mdr.testeandroid.throwable.AppThrowable
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseRepository(
    private val gson: Gson
) {

    protected fun <T> handleResponse(response: Response<T>): T? {
        if (response.isSuccessful) {
            return response.body()
        } else {
            val apiErrorList = response.errorBody()?.charStream()?.let { errorJson ->
                gson.fromJsonOrNull<ErrorWrapperApiModel>(errorJson)
            }
            val errorList = apiErrorList?.toBusinessModel() ?: run {
                ErrorList(true, emptyList())
            }
            throw AppThrowable(errorList)
        }
    }
}
package br.com.mdr.testeandroid.throwable

import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.ErrorList
import br.com.mdr.testeandroid.model.business.ErrorType
import retrofit2.HttpException

class AppThrowable(
    val errors: ErrorList
) : Throwable() {

    companion object {

        fun fromHttpException(httpException: HttpException): AppThrowable {
            return when (httpException.code()) {
                404 -> AppThrowable(errors = ErrorList(list = listOf(notFoundError()), hasError = true))
                else -> AppThrowable(errors = ErrorList(list = listOf(unknownError()), hasError = true))
            }
        }

        fun fromUnknownHostException(): AppThrowable {
            return AppThrowable(errors = ErrorList(list = listOf(notFoundError()), hasError = true))
        }

        fun fromThrowable(t: Throwable): AppThrowable {
            return AppThrowable(errors = ErrorList(list = listOf(unknownError()), hasError = true))
        }

        private fun notFoundError(): Error = Error(errorType = ErrorType.NOT_FOUND)

        private fun unknownError(): Error = Error(errorType = ErrorType.UNKNOWN)
    }
}
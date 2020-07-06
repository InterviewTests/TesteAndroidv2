package br.com.mdr.testeandroid.extensions

import br.com.mdr.testeandroid.throwable.AppThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Marlon D. Rocha
 * @since 04/07/2020
 */

typealias ErrorListener = (AppThrowable) -> Unit

fun Any.scopeWithErrorHandling(errorListener: ErrorListener): CoroutineScope {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        val umaThrowable = when (throwable) {
            is AppThrowable -> throwable
            is HttpException -> AppThrowable.fromHttpException(throwable)
            is UnknownHostException -> AppThrowable.fromUnknownHostException()
            else -> AppThrowable.fromThrowable(throwable)
        }
        errorListener(umaThrowable)
    }
    return CoroutineScope(errorHandler)
}

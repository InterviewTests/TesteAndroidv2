package com.br.myapplication.domain.usecase

import com.br.myapplication.data.service.ApiResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase <Type, in Params> where Type : Any {

    private var parentJob: Job = Job()
    private var backgroundContext = Dispatchers.IO
    private var foregroundContext = Dispatchers.Main

    abstract suspend fun run(param: Params): Type

    operator fun invoke(param: Params, onResult: (ApiResult<Type>) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler {
                _: CoroutineContext, throwable: Throwable ->
                 onResult(ApiResult.Error(throwable))
        }
       parentJob = Job()
       CoroutineScope(foregroundContext + parentJob + exceptionHandler).launch {
           val result = withContext(backgroundContext) { run(param) }
           onResult(ApiResult.Success(result))
       }
    }
}



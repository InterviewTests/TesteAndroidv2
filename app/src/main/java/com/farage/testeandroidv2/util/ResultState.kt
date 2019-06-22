package com.farage.testeandroidv2.util

data class ResultState<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val message: String? = null
) {

    companion object {

        fun <T> success(data: T): ResultState<T> = ResultState(ResultType.SUCCESS, data)

        fun <T> error(message: String): ResultState<T> = ResultState(ResultType.ERROR, message = message)

        fun <T> emptyData(): ResultState<T>? = ResultState(ResultType.EMPTY_DATA)
    }

}
package br.com.learncleanarchitecture.util

import br.com.learncleanarchitecture.login.data.api.Error

interface Response<T> : OnResult<T> {
    fun onError(response: Error)
    fun onThrowable(response: Throwable)
}
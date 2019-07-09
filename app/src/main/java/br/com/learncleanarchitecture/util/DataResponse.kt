package br.com.learncleanarchitecture.util

import br.com.learncleanarchitecture.login.data.api.Error

open class DataResponse<T> : Response<T> {
    private var data: Data<T>? = null

    fun setData(data: Data<T>) {
        this.data = data
    }

    fun getData(): Data<T> {
        return data!!
    }

    override fun onSuccess(response: T) {
    }

    override fun onError(response: Error) {

    }

    override fun onThrowable(response: Throwable) {
    }


}
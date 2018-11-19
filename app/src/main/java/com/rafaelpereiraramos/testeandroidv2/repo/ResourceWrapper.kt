package com.rafaelpereiraramos.testeandroidv2.repo

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
data class ResourceWrapper<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResourceWrapper<T> {
            return ResourceWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.LOADING, data, null)
        }
    }
}
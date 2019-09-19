package com.develop.tcs_bank.infrastructure.utils

class Resource<T> {
    var throwable: Throwable? = null
    var status: Status = Status.SUCCESS
    var userAccount: T? = null
    var message: String = ""

    companion object {
        // API Retornou 200 com success TRUE
        fun <T> success(data: T): Resource<T> {
            val resource = Resource<T>()
            resource.status = Status.SUCCESS
            resource.userAccount = data
            resource.message = ""
            return resource
        }

        // API Retornou 200 com success FALSE
        fun <T> message(msg: String): Resource<T> {
            val resource = Resource<T>()
            resource.status = Status.MESSAGE
            resource.userAccount = null
            resource.message = msg
            return resource
        }

        // ERROR de requisição, Ex timeout, sem conexão Etc.
        fun <T> failure(t: Throwable): Resource<T> {
            val resource = Resource<T>()
            resource.status = Status.FAILURE
            resource.userAccount = null
            resource.throwable = t
            resource.message = ""
            return resource
        }

        // API Retornou 300/400/500
        fun <T> error(msg: String): Resource<T> {
            val resource = Resource<T>()
            resource.status = Status.ERROR
            resource.userAccount = null
            resource.throwable = null
            resource.message = msg
            return resource
        }

        // Antes de qualquer chamada é enviado imediatamente um loading para camadas de UI.
        fun <T> loading(): Resource<T> {
            val resource = Resource<T>()
            resource.status = Status.LOADING
            resource.userAccount = null
            resource.throwable = null
            resource.message = ""
            return resource
        }
    }

    enum class Status {
        SUCCESS, MESSAGE, ERROR, LOADING, FAILURE
    }
}
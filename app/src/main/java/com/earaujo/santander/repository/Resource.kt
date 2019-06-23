package com.earaujo.santander.repository

import com.earaujo.santander.repository.Status.*

class Resource<T> constructor(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}

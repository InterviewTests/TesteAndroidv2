package com.ygorcesar.testeandroidv2.base.common.network

import com.ygorcesar.testeandroidv2.base.common.exception.ServerError


open class BaseResponse(open val data: Any?, open val error: ResponseError?) {

    fun validateError() {
        if (error != null && error?.code != null && error?.message != null) {
            throw ServerError(error!!)
        }
    }
}
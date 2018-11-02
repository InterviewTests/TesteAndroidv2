package com.ygorcesar.testeandroidv2.base.common.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(val data: T, val error: ResponseError?)
package com.ygorcesar.testeandroidv2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.ygorcesar.testeandroidv2.base.common.network.BaseResponse
import com.ygorcesar.testeandroidv2.base.common.network.ResponseError

@JsonClass(generateAdapter = true)
data class StatementsResponse(
    @property:Json(name = "statementList") override val data: List<StatementRaw>?,
    override val error: ResponseError
) : BaseResponse(data, error)
package com.ygorcesar.testeandroidv2.login.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.ygorcesar.testeandroidv2.base.common.network.BaseResponse
import com.ygorcesar.testeandroidv2.base.common.network.ResponseError

@JsonClass(generateAdapter = true)
data class UserAccountResponse(
    @property:Json(name = "userAccount") override val data: UserAccountRaw?,
    override val error: ResponseError?
) : BaseResponse(data, error)

@JsonClass(generateAdapter = true)
data class UserAccountRaw(
    @Json(name = "userId") var userId: Int?,
    @Json(name = "name") var name: String?,
    @Json(name = "bankAccount") var bankAccount: String?,
    @Json(name = "agency") var agency: String?,
    @Json(name = "balance") var balance: Double?
)


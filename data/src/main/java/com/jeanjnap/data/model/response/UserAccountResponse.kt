package com.jeanjnap.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class UserAccountResponse(
    @Json(name = "userId") val userId: Long?,
    @Json(name = "name") val name: String?,
    @Json(name = "bankAccount") val bankAccount: String?,
    @Json(name = "agency") val agency: String?,
    @Json(name = "balance") val balance: BigDecimal?
)

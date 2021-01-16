package com.jeanjnap.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataResponse(
    @Json(name = "userAccount") val userAccount: UserAccountResponse
)
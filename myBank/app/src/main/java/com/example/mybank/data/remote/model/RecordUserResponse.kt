package com.example.mybank.data.remote.model

data class RecordUserResponse(
    val userAccount: RecordUser?,
    val error: RecordError?
)
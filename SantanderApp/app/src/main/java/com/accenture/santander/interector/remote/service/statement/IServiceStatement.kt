package com.accenture.santander.interector.remote.service.statement

import com.accenture.santander.entity.ListStatement
import retrofit2.Response

interface IServiceStatement {

    fun statement(
        idUser: Int,
        success: (note: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}
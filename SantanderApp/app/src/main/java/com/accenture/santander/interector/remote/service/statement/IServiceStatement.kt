package com.accenture.santander.interector.remote.service.statement

import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.ListStatement
import retrofit2.Response

interface IServiceStatement {

    fun statement(
        idUser: Int,
        success: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}
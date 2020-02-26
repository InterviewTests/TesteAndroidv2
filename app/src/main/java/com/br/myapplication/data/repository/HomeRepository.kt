package com.br.myapplication.data.repository

import com.br.myapplication.data.model.ResponseStatement
import com.br.myapplication.data.service.Requests

class HomeRepository(private val requests: Requests) {

    suspend fun getStatements(id: String) : ResponseStatement = requests.getStatements(id)
}
package com.br.myapplication.repository

import com.br.myapplication.model.ResponseStatement
import com.br.myapplication.service.Requests

class HomeRepository(private val requests: Requests) {

    suspend fun getStatements(id: String) : ResponseStatement = requests.getStatements(id)
}
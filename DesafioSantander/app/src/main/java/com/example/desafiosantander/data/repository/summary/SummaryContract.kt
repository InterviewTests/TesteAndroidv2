package com.example.desafiosantander.data.repository.summary

import com.example.desafiosantander.data.model.response.StatementResponse
import io.reactivex.Flowable

interface SummaryContract {
    fun statementList(idUser: Int): Flowable<StatementResponse>
}
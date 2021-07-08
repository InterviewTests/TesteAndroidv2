package com.example.desafiosantander.data.repository.summary

import com.example.desafiosantander.data.api.ApiService
import com.example.desafiosantander.data.model.response.StatementResponse
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class SummaryRepository(private val apiService: ApiService) : SummaryContract {

    override fun statementList(idUser: Int): Flowable<StatementResponse> {
        return apiService.statementList(idUser)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
}
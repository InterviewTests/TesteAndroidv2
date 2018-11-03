package com.ygorcesar.testeandroidv2.home.domain

import com.ygorcesar.testeandroidv2.home.data.HomeRepository
import com.ygorcesar.testeandroidv2.home.model.Statement
import io.reactivex.Single
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val homeRepository: HomeRepository) {


    fun getStatements(userId: Int): Single<List<Statement>> {
        return homeRepository.getStatements(userId)
    }

}
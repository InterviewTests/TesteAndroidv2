package br.com.alex.bankappchallenge.features.statement

import br.com.alex.bankappchallenge.di.androidModule
import br.com.alex.bankappchallenge.di.interactorModule
import br.com.alex.bankappchallenge.di.mapperModule
import br.com.alex.bankappchallenge.di.networkModule
import br.com.alex.bankappchallenge.di.reducerModule
import br.com.alex.bankappchallenge.di.repositoryModule
import br.com.alex.bankappchallenge.di.viewModelModule
import br.com.alex.bankappchallenge.feature.statement.StatementViewModel
import br.com.alex.bankappchallenge.rules.LocalTestRule
import br.com.alex.bankappchallenge.rules.RxLocalRule
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.inject

class StatementViewModelTest : KoinTest {
    private val statementViewModel: StatementViewModel by inject()

    @get:Rule
    val rxRule = RxLocalRule()

    @get:Rule
    val localTestRule = LocalTestRule(
        mutableListOf(
            networkModule,
            viewModelModule,
            androidModule,
            interactorModule,
            reducerModule,
            repositoryModule,
            mapperModule
        )
    )
}
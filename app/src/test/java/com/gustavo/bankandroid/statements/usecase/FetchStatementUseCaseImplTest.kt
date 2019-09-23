package com.gustavo.bankandroid.statements.usecase

import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.statements.data.gson.StatementList
import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import com.gustavo.bankandroid.statements.data.mapper.StatementMapper
import com.gustavo.bankandroid.statements.repository.DataRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchStatementUseCaseImplTest {

    private val repository = mock<DataRepository>()
    private val mapper = StatementMapper()

    private lateinit var useCase: FetchStatementUseCaseImpl

    private lateinit var statementResponse: StatementResponse

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        setupMocks()
    }

    private fun setupMocks() {
        statementResponse = mockStatementResponse()
        whenever(repository.getUserStatementList(any(), any()))
            .thenReturn(Single.just(statementResponse))

        useCase = FetchStatementUseCaseImpl(repository, mapper)
    }

    @Test
    fun `if repository returns response, usecase return the equivalent list`() {
        val testSingleObserver = TestObserver<List<UserStatementItem>>()
        val result = useCase.execute(4, "123")
        result.subscribe(testSingleObserver)

        assertEquals(testSingleObserver.valueCount(), 1)
        assertEquals(testSingleObserver.values()[0].size, statementResponse.statementList.size)
    }

    @Test
    fun `usecase preserves repository list order`() {
        val testSingleObserver = TestObserver<List<UserStatementItem>>()
        val result = useCase.execute(4, "123")
        result.subscribe(testSingleObserver)

        val list = testSingleObserver.values()[0]
        assertEquals(list[0].title, statementResponse.statementList[0].title)
        assertEquals(list[1].title, statementResponse.statementList[1].title)
        assertEquals(list[2].title, statementResponse.statementList[2].title)
    }

    private fun mockStatementResponse(): StatementResponse {
        return StatementResponse(
            listOf(
                StatementList("title1", "desc1", "date", 100f),
                StatementList("title2", "desc2", "date", 200f),
                StatementList("title3", "desc3", "date", 3300f)
            ),
            Error()
        )
    }
}
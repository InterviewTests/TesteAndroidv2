package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.repository.DataRepository
import com.gustavo.bankandroid.mock.MockData
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

    private lateinit var useCase: FetchStatementUseCaseImpl

    private lateinit var repositoryList: List<UserStatementItem>

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        setupMocks()
    }

    private fun setupMocks() {
        repositoryList = MockData.mockStatementList()
        whenever(repository.getUserStatementList(any()))
            .thenReturn(Single.just(MockData.mockStatementList()))

        useCase = FetchStatementUseCaseImpl(repository)
    }

    @Test
    fun `if repository returns response, usecase return the equivalent list`() {
        val testSingleObserver = TestObserver<List<UserStatementItem>>()
        val result = useCase.execute(4)
        result.subscribe(testSingleObserver)

        assertEquals(testSingleObserver.valueCount(), 1)
        assertEquals(testSingleObserver.values()[0].size, repositoryList.size)
    }

    @Test
    fun `usecase preserves repository list order`() {
        val testSingleObserver = TestObserver<List<UserStatementItem>>()
        val result = useCase.execute(4)
        result.subscribe(testSingleObserver)

        val list = testSingleObserver.values()[0]
        assertEquals(list[0].title, repositoryList[0].title)
        assertEquals(list[1].title, repositoryList[1].title)
        assertEquals(list[2].title, repositoryList[2].title)
    }
}
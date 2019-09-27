package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetLoggedUserInfoUseCaseImplTest {

    private val userRepository: RepositoriesContract.UserRepository = mock()
    private val userCase = GetLoggedUserInfoUseCaseImpl(userRepository)

    private val userInfo: UserInfo = MockData.mockUserInfo()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

    }


    @Test
    fun execute() {
        whenever(userRepository.getSavedUser()).thenReturn(Single.just(userInfo))
        userCase.execute()
        verify(userRepository).getSavedUser()

        val testObserver = TestObserver<UserInfo>()
        val result = userRepository.getSavedUser()
        result.subscribe(testObserver)

        assertEquals(testObserver.values()[0].userId, userInfo.userId)
    }
}
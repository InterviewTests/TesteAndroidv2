package com.farage.testeandroidv2.repository

import com.farage.testeandroidv2.datasource.UserDataSource
import com.farage.testeandroidv2.datasource.model.UserAccountResponse
import com.farage.testeandroidv2.datasource.model.UserResponse
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.ResultType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertEquals

class UserRepositoryImplTest {

    private val userDataSource: UserDataSource = mock()
    private val userRepositoryImpl = UserRepositoryImpl(userDataSource)

    @Test
    fun userLogin_ShouldReturnSuccess() = runBlocking {
        whenever(userDataSource.doUserLogin("", "")).thenReturn(
            ResultState.success(
                UserResponse(
                    UserAccountResponse(
                        "", "", "", "", 0.0
                    )
                )
            )
        )

        val result = userRepositoryImpl.userLogin("", "")
        assertEquals(ResultType.SUCCESS, result.resultType)
    }
}
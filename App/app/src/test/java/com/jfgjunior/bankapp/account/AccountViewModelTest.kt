package com.jfgjunior.bankapp.account

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jfgjunior.bankapp.RxSchedulerRule
import com.jfgjunior.bankapp.data.external.Repository
import com.jfgjunior.bankapp.data.models.ResponseError
import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.Statement
import com.jfgjunior.bankapp.data.models.User
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class AccountViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify if valid user is delivered to the view`() {
        val statement = Statement("title", "description", "2020-12-12", 0f)
        val transactionsRepo = mockk<Repository>(relaxed = true) {
            every { getTransactions(0) } returns Single.just(
                ResponseWrapper(
                    listOf(statement),
                    ResponseError(0, "")
                )
            )
        }

        val user = mockk<User> {
            every { id } returns 0
        }

        val accountViewModel = AccountViewModel(user, transactionsRepo)

        val observer = Observer<List<Statement>> {}

        try {
            accountViewModel.statementListSuccess.observeForever(observer)
            val expected = listOf(statement)
            val result = accountViewModel.statementListSuccess.value

            assertEquals(expected, result)
        } finally {
            accountViewModel.statementListSuccess.removeObserver(observer)
        }
    }
}
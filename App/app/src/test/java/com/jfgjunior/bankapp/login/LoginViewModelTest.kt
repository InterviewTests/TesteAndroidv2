package com.jfgjunior.bankapp.login

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jfgjunior.bankapp.RxSchedulerRule
import com.jfgjunior.bankapp.data.external.Repository
import com.jfgjunior.bankapp.data.models.ResponseError
import com.jfgjunior.bankapp.data.models.ResponseWrapper
import com.jfgjunior.bankapp.data.models.User
import com.jfgjunior.bankapp.data.models.UserCredentials
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
class LoginVewModelTest {

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
        val user = "test_user@gmail.com"
        val password = "Test@1"

        val successRepo = mockk<Repository>(relaxed = true) {
            every { loginUser(UserCredentials(user, password)) } returns Single.just(
                ResponseWrapper(
                    User(1, "name", "1", "1", 0f),
                    ResponseError(0, "")
                )
            )
            every { getUser() } returns null
        }

        val loginViewModel = LoginViewModel(successRepo)
        loginViewModel.user = user
        loginViewModel.password = password
        val observer = Observer<User> {}
        loginViewModel.tryLogin()

        try {
            loginViewModel.successLogin.observeForever(observer)

            val expected = 1 //User ID
            val result = loginViewModel.successLogin.value?.id

            assertEquals(expected, result)

        } finally {
            loginViewModel.successLogin.removeObserver(observer)
        }
    }

    @Test
    fun `verify if invalid user triggers the invalid user live data`() {
        val user = ""
        val password = "Test@1"

        val failUserRepo = mockk<Repository>(relaxed = true) {
            every { loginUser(UserCredentials(user, password)) } returns Single.just(
                ResponseWrapper(
                    User(1, "name", "1", "1", 0f),
                    ResponseError(0, "")
                )
            )
            every { getUser() } returns null
        }

        val loginViewModel = LoginViewModel(failUserRepo)
        loginViewModel.user = user
        loginViewModel.password = password
        val observer = Observer<Boolean> {}
        loginViewModel.tryLogin()

        try {
            loginViewModel.invalidUser.observeForever(observer)

            val expected = false
            val result = loginViewModel.invalidUser.value

            assertEquals(expected, result)

        } finally {
            loginViewModel.invalidUser.removeObserver(observer)
        }
    }

    @Test
    fun `verify if invalid password triggers the invalid password live data`() {
        val user = "test_user@gmail.com"
        val password = ""

        val failPasswordRepo = mockk<Repository>(relaxed = true) {
            every { loginUser(UserCredentials(user, password)) } returns Single.just(
                ResponseWrapper(
                    User(1, "name", "1", "1", 0f),
                    ResponseError(0, "")
                )
            )
            every { getUser() } returns null
        }

        val loginViewModel = LoginViewModel(failPasswordRepo)
        loginViewModel.user = user
        loginViewModel.password = password
        val observer = Observer<Boolean> {}
        loginViewModel.tryLogin()

        try {
            loginViewModel.invalidPassword.observeForever(observer)

            val expected = false
            val result = loginViewModel.invalidPassword.value

            assertEquals(expected, result)

        } finally {
            loginViewModel.invalidPassword.removeObserver(observer)
        }
    }
}
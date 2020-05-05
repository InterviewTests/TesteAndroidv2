package com.paulokeller.bankapp

import androidx.lifecycle.*
import com.paulokeller.bankapp.ui.login.LoginViewModel
import com.paulokeller.bankapp.data.models.Account
import com.paulokeller.bankapp.data.models.AppState
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

inline fun <reified T> lambdaMock(): T = mock(T::class.java)

@RunWith(JUnit4::class)
class LoginViewModelTest {
    @Test
    fun testLoginViewModelLogin(){
        val observer = lambdaMock<(AppState<Account>) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val viewModel = LoginViewModel()
        observeTitleChanges(viewModel.loginState, lifecycle,observer)

        viewModel.login("user_test", "Test@1")
    }

    private fun observeTitleChanges(liveData: MutableLiveData<AppState<Account>>, lifecycle: Lifecycle, observer: (AppState<Account>) -> Unit) {
        liveData.observe({ lifecycle }) { title ->
            title?.let(observer)
        }
    }
}
